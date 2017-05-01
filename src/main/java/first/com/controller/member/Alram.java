package first.com.controller.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import first.com.dao.AlramDAO;
import first.com.model.NotiDTO;

@Controller
public class Alram {

	@Resource
	private AlramDAO noti;

	@RequestMapping(value = "/notilist.do")
	@ResponseBody
	public List<NotiDTO> alramList(@RequestParam(value = "session_id") int member_id, Model model) {

		System.out.println(member_id);
		
		//Notification���̺� �ִ� ���� �� ���� ���� ���� ȸ���� session_id�� �̿��Ͽ� ��ġ�ϴ� �������� �����´�.
		//NotiDTO�� ���� �־ �����ϱ� ������ json��ü�� Ű�� DTO�� �� property�̴�.
		List<NotiDTO> list = noti.notiList(member_id);
		
		return list;
	}

	public String deleteAlram() {

		return null;
	}

}
