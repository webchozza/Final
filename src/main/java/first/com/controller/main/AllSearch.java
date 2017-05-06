package first.com.controller.main;

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
	
	private int totalCount; // �� �Խù��� ��
	private int blockCount = 10; // �� �������� �Խù��� ��
	private int blockPage = 5; // �� ȭ�鿡 ������ ������ ��
	private String pagingHtml; // ����¡�� ������ HTML
	private AjaxPaging page; // ����¡ Ŭ����
	private String path = "AllSearchList";//if (RequestMapping("/here.do")) => here = path
	private String[] category = { "board_date", "board_like", "board_comment_count", "scrap_count", "board_hit" };
	@Resource
	private MainDAO mainSearch;
	
	@RequestMapping("/AllSearchList.do")
	public String allSearch(@RequestParam(value="soundsearch", defaultValue="") String search,
							@RequestParam(value="AllSearch", required=false) String AllSearch,
							@RequestParam(value="n", defaultValue="0") int n,
							@RequestParam(value="currentPage", defaultValue="1") int currentPage, 
							@RequestParam(value="ap", required=false) String ap,
							Model model){

		if(AllSearch != null){ search = AllSearch; }

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("category", category[n]);

		List<BoardDTO> list = mainSearch.AllSearch(map);
		
		totalCount = list.size();
		
		page = new AjaxPaging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		int lastCount = totalCount;

		if (page.getEndCount() < totalCount){ lastCount = page.getEndCount() + 1; }
		
		list= list.subList(page.getStartCount(), lastCount);

		model.addAttribute("allSearchList", list);
		model.addAttribute("page", pagingHtml);
		
		model.addAttribute("n", n);//select���� selected �Ӽ� �ο��� ���� ���� ������ �ʿ�
		
		//ajax�� �̿��� �˻��� �����ϱ� ���� �־� �����ش�
		model.addAttribute("i", currentPage);
		model.addAttribute("path", page.getFullPath());
		model.addAttribute("AllSearch", search);
		
		if(ap != null){
			return "main/AllSearchList";//at Ajax request
		}
		
		
		return "AllSearchList";
	}

}
