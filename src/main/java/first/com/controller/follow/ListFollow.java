package first.com.controller.follow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.common.AjaxPaging;
import first.com.dao.FollowDAO;
import first.com.dao.MemberPageDAO;
import first.com.model.FollowDTO;

@Controller
public class ListFollow {
	
	@Resource
	private MemberPageDAO memberpage;
	
	@Resource
	private FollowDAO followService;
	
	private int startrow;
	private int endrow;
	private int totalCount; // �� �Խù��� ��
	private int blockCount = 10; // �� �������� �Խù��� ��
	private int blockPage = 5; // �� ȭ�鿡 ������ ������ ��
	private String pagingHtml; // ����¡�� ������ HTML
	private AjaxPaging page; // ����¡ Ŭ����
	private String path = "ListFollow";//if (RequestMapping("/here.do")) => here = path
	
	@RequestMapping("/ListFollow.do")
	public String addFollow(@RequestParam(value="member_id", defaultValue="0") int member_id,
							@RequestParam(value="n", defaultValue="0") int n,
							@RequestParam(value="search", required=false, defaultValue="") String search,
							@RequestParam(value="currentPage", defaultValue="1") int currentPage,
							@RequestParam(value="ap", required=false) String ap,
							Model model){
		
		startrow = ((currentPage-1) * blockCount)+1;
		endrow = (startrow + blockCount)-1;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("member_id", member_id);
		map.put("search", search);
		
		List<FollowDTO> list = followService.listFollow(map);
		Map<String, Object> myCount = memberpage.myCount(map);
		
		totalCount = followService.followAllCount(map);
		
		page = new AjaxPaging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		model.addAttribute("list", list);
		model.addAttribute("myCount", myCount);
		
		model.addAttribute("page", pagingHtml);
		
		model.addAttribute("n", n);
		
		model.addAttribute("i", currentPage);
		model.addAttribute("path", page.getFullPath());
		model.addAttribute("member_id", member_id);
		
		if(ap != null){
			return "memberpage/FollowList";//at Ajax request
		}

		return null;//�ȷο� �������� ���� �����ʹٸ� �ش� �������� ���� Ÿ���� id�� ���ϰ����� �ۼ�
	}
	

}
