package first.com.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/retest.do")
	@ResponseBody
	public List<Map<String, Object>> test(){
		Map<String, Object> map = new HashMap();
		map.put("member_id", 300);
		List<Map<String, Object>> recommend_list =  recommendService.recommendList(map);
		
		System.out.println(recommend_list);
		return recommend_list;
	}
}
