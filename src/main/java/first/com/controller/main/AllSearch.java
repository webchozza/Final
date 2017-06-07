package first.com.controller.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.common.AjaxPaging;
import first.com.dao.MainDAO;
import first.com.model.BoardDTO;

@Controller
public class AllSearch {
	
	@Resource
	private MainDAO mainSearch;
	
	private int startrow;
	private int endrow;
	private int totalCount; // 총 게시물의 수
	private int blockCount = 10; // 한 페이지의 게시물의 수
	private int blockPage = 5; // 한 화면에 보여줄 페이지 수
	private String pagingHtml; // 페이징을 구현한 HTML
	private AjaxPaging page; // 페이징 클래스
	private String path = "AllSearchList";//if (RequestMapping("/here.do")) => here = path
	private String[] category = { "board_date", "board_like", "board_comment_count", "scrap_count", "board_hit" };
	
	@RequestMapping("/AllSearchList.do")
	public String allSearch(@RequestParam(value="soundsearch", defaultValue="") String search,
							@RequestParam(value="AllSearch", required=false) String AllSearch,
							@RequestParam(value="n", defaultValue="0") int n,
							@RequestParam(value="currentPage", defaultValue="1") int currentPage, 
							@RequestParam(value="ap", required=false) String ap,
							Model model){

		if(AllSearch != null){ search = AllSearch; }
		/////
		List<String> searchtokenlist = new ArrayList<String>();
		//true면 검색어에 공백 포함, false면 검색어에 공백 미포함
		boolean check = false;
		//만약 검색어가 공백 기준으로 여러개라면 나눠서 따로 저장한다.
		for(int i=0; i<search.length(); i++){
			//검색어에 공백이 있다면
			if(search.charAt(i) == ' '){
				String[] searchsplit = search.split("\\s");
				for(int k=0;k<searchsplit.length;k++){
					searchtokenlist.add(k, searchsplit[k].trim());
				}
				check = true;
				break;
			}
		}
		//검색어에 공백이 없다면
		if(check == false){
			searchtokenlist.add(0, search);
		}
		//////
		startrow = ((currentPage-1) * blockCount)+1;
		endrow = (startrow + blockCount)-1;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("searchlist",searchtokenlist);
		map.put("category", category[n]);
		System.out.println(map.get("searchlist"));
		List<BoardDTO> list = mainSearch.allSearch(map);

		totalCount = mainSearch.allBordCount(map);

		page = new AjaxPaging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		model.addAttribute("allSearchList", list);
		model.addAttribute("page", pagingHtml);
		
		model.addAttribute("n", n);//select문의 selected 속성 부여를 위한 조건 구현에 필요
		
		//ajax를 이용한 검색을 구현하기 위해 넣어 보내준다
		model.addAttribute("i", currentPage);
		model.addAttribute("path", page.getFullPath());
		model.addAttribute("AllSearch", search);
		
		if(ap != null && !ap.equals("AjaxPage")){
			model.addAttribute("mpch", "on");
			return "main/AllSearchList";//at Ajax request
		}else if(ap != null && ap.equals("AjaxPage")){
			return "main/AllSearchPage";
		}
		model.addAttribute("mpch", "on");
		return "AllSearchList";
	}

}
