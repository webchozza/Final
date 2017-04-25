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

import first.com.dao.ScrapDAO;
import first.com.model.BoardDTO;
import first.com.model.ScrapDTO;

@Controller
public class Scrap {
	
	@Resource
	private ScrapDAO Scrap;
	
	Map<String, Object> map = new HashMap<String, Object>();
			
	@RequestMapping(value="/ScrapList.do")
	public String scrapList(@RequestParam(value="id", required=false, defaultValue="0") int id, 
							@RequestParam(value="search", required=false, defaultValue="") String search,
							Model model){
		
		map.put("member_id", 1000);
		map.put("search", search);
		
		//���� ���̵� ���۹޾Ƽ� �Ķ���� ������ �Ѱ��ش�
		List<BoardDTO> list = Scrap.scrapList(map);
		
		model.addAttribute("board", list);
		
		return "ScrapList";
		
	}
	
	
	//������ �Ǹ� ajax�� �����غ���(js������ ���� ����� ��ũ����ư�� Ŭ������ �� ajax�� �� �޼ҵ�� ��û�� ���� �� ������ �����鼭 alertâ�� ����� �� �� ������ ���¸� �����ϵ��� ����
	@RequestMapping(value="/ScrapInsert.do")
	public String insertScrap(ScrapDTO scrap, HttpServletResponse response) throws IOException{
		
		Scrap.insertScrap(scrap);
		
		response.setContentType("text/html; charset=UTF-8"); //ĳ���ͼ� ����(�ѱ� ���)
		PrintWriter out = response.getWriter();
		out.println("<script>alert('��ũ�� �Ǿ����ϴ�.'); history.go(-1); </script>");
		out.close();
		
		return "ScrapInsert";//��ũ��Ʈ�� �������� �ʴ´ٸ� ��ũ�� ������� �̵�
	}
	
	@RequestMapping(value="/ScrapDelete.do")
	public String deleteScrap(@RequestParam(value="scrap_id") int scrap_id){
		
		Scrap.deleteScrap(scrap_id);
		
		return "ScrapDelete";
	}

}
