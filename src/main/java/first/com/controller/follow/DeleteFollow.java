package first.com.controller.follow;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import first.com.dao.FollowDAO;
import first.com.model.FollowDTO;

@Controller
public class DeleteFollow {
	
	@Resource
	private FollowDAO followService;
	
	//ajax�� ó������
	@RequestMapping("/DeleteFollow.do")
	@ResponseBody
	public int addFollow(FollowDTO follow){
		
		followService.deleteFollow(follow);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_id", follow.getMember_id());//�ȷο� �ϴ� ���
		map.put("member_id", follow.getFollow_member_id());//�ȷο� ���ϴ� ���

		return followService.followCheck(map);
	}
	

}
