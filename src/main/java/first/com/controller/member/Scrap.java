package first.com.controller.member;

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
import first.com.dao.ScrapDAO;
import first.com.model.BoardDTO;
import first.com.model.ScrapDTO;

@Controller
public class Scrap {
	
	@Resource
	private ScrapDAO Scrap;
	
	private int totalCount; // �� �Խù��� ��
	private int blockCount = 10; // �� �������� �Խù��� ��
	private int blockPage = 5; // �� ȭ�鿡 ������ ������ ��
	private String pagingHtml; // ����¡�� ������ HTML
	private AjaxPaging page; // ����¡ Ŭ����
	private String path = "ScrapList";//if (RequestMapping("/here.do")) => here = path
	
	
	Map<String, Object> map = new HashMap<String, Object>();
			
	@RequestMapping(value="/ScrapList.do")
	public String scrapList(@RequestParam(value="member_id", required=false, defaultValue="0") int member_id, 
							@RequestParam(value="n", defaultValue="0") int n,
							@RequestParam(value="search", required=false, defaultValue="") String search,
							@RequestParam(value="currentPage", defaultValue="1") int currentPage,
							@RequestParam(value="ap", required=false) String ap,
							Model model){
		
		map.put("member_id", member_id);//�׽�Ʈ������ ���� �ٲ�����Ѵ�
		map.put("search", search);
		
		//���� ���̵� ���۹޾Ƽ� �Ķ���� ������ �Ѱ��ش�
		List<BoardDTO> list = Scrap.scrapList(map);
		
		totalCount = list.size();
		
		page = new AjaxPaging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		int lastCount = totalCount;

		if (page.getEndCount() < totalCount){ lastCount = page.getEndCount() + 1; }
		
		list= list.subList(page.getStartCount(), lastCount);
		
		model.addAttribute("board", list);
		model.addAttribute("page", pagingHtml);
		
		model.addAttribute("n", n);
		
		model.addAttribute("i", currentPage);
		model.addAttribute("path", page.getFullPath());
		model.addAttribute("member_id", member_id);
		
		if(ap != null){
			return "scrap/ScrapList";//at Ajax request
		}
		
		return "ScrapList";
		
	}
	
	
	//������ �Ǹ� ajax�� �����غ���(js������ ���� ����� ��ũ����ư�� Ŭ������ �� ajax�� �� �޼ҵ�� ��û�� ���� �� ������ �����鼭 alertâ�� ����� �� �� ������ ���¸� �����ϵ��� ����
	@RequestMapping(value="/ScrapInsert.do")
	public String insertScrap(ScrapDTO scrap, HttpServletResponse response) throws IOException{
		
		Scrap.insertScrap(scrap);
		
		response.setContentType("text/html; charset=UTF-8"); //ĳ���ͼ� ����(�ѱ� ���)
		PrintWriter out = response.getWriter();
		out.println("<script>alert('��ũ�� �Ǿ����ϴ�.'); history.go(-1); </script>");//�̰͵� Ajax�� �ٽ� ��������(��� ������ callback�Լ����� alertâ ����ָ� �ȴ�.)
		out.close();
		
		return "ScrapInsert";//��ũ��Ʈ�� �������� �ʴ´ٸ� ��ũ�� ������� �̵�
	}
	
	@RequestMapping(value="/ScrapDelete.do")
	public String deleteScrap(@RequestParam(value="scrap_id") int scrap_id){
		
		Scrap.deleteScrap(scrap_id);
		
		return "ScrapDelete";
	}

}
