package first.com.controller.memberpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.common.AjaxPaging;
import first.com.dao.FollowDAO;
import first.com.dao.MemberPageDAO;
import first.com.model.BoardDTO;
import first.com.model.FollowDTO;

@Controller
public class MemberPage {
	
	@Resource
	private MemberPageDAO memberpage;
	
	@Resource
	private FollowDAO followService;
	
	private int startrow;
	private int endrow;
	private int totalCount; // 총 게시물의 수
	private int blockCount = 10; // 한 페이지의 게시물의 수
	private int blockPage = 5; // 한 화면에 보여줄 페이지 수
	private String pagingHtml; // 페이징을 구현한 HTML
	private AjaxPaging page; // 페이징 클래스
	private String path = "MemberPage";//if (RequestMapping("/here.do")) => here = path
	int followCheck;
	
	//회원정보 화면 게시물 목록
	@RequestMapping("/MemberPage.do")
	public String followMain(@RequestParam(value="member_id", defaultValue="-1") int member_id, 
							 @RequestParam(value="session_id", defaultValue="0") int session_id, 
							 @RequestParam(value="n", defaultValue="0") int n,
							 @RequestParam(value="search", required=false, defaultValue="") String search,
							 @RequestParam(value="currentPage", defaultValue="1") int currentPage,
							 @RequestParam(value="ap", required=false) String ap,
							 Model model) throws IOException{
		
		startrow = ((currentPage-1) * blockCount)+1;
		endrow = (startrow + blockCount)-1;

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("member_id", member_id);
		map.put("search", search);

		List<BoardDTO> list = memberpage.myBoardList(map);
		Map<String, Object> myCount = memberpage.myCount(map);
		FollowDTO follow = followService.followCount(member_id);

		if(session_id != 0){
		map.put("session_id", session_id);
		followCheck = followService.followCheck(map);
		}

		totalCount = memberpage.myBoardCount(map);
		
		page = new AjaxPaging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		model.addAttribute("list", list);
		model.addAttribute("myCount", myCount);
		model.addAttribute("followCount", follow);
		
		model.addAttribute("page", pagingHtml);
		
		model.addAttribute("n", n);
		
		model.addAttribute("i", currentPage);
		model.addAttribute("path", page.getFullPath());
		model.addAttribute("member_id", member_id);
		
		if(session_id == 0 || session_id == member_id){
			model.addAttribute("followCheck", "me");
		} else if(session_id != 0){ 
			model.addAttribute("followCheck", followCheck);
		}

		if(ap != null){
		if(ap.equals("PushState")){
			return "memberpage/MemberPage";
		}else{
			return "memberpage/MyBoard";//at Ajax request
			}
		}

		model.addAttribute("mpch", "on");
		return "MemberPage";
	}

}
