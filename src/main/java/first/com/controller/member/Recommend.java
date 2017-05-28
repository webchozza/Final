package first.com.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import first.com.dao.RecommendDAO;

@Controller
public class Recommend {
	
	@Resource
	private RecommendDAO recommendService;
	
	//��õ �� �� ��õ���̺� ���� �Է�
	public void addRecommend(Map<String, Object> map){
		recommendService.addRecommend(map);
	}
	
	/*@RequestMapping(value="/RecommendList.do", method=RequestMethod.GET)
	public String Main(){
		return "redirect:main.do";
	}*/
	
	//recommend board controller
	@RequestMapping(value="/RecommendList.do")
	@ResponseBody
	public List<HashMap<String, Object>> RecommendList(@RequestParam("session_id") int member_id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		
		List<HashMap<String, Object>> recommend_list =  recommendService.recommendList(map);
		
		System.out.println(recommend_list);
		//ajax �ۼ��� �� list�� null�� �ƴ� �� << ���ǹ� �� �ۼ�
		return recommend_list;
	}
}
