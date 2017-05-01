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
		/*List<NotiDTO> list = noti.notiList(member_id);*/
		List list = new ArrayList();
		NotiDTO no = new NotiDTO();
		
		no.setNoti_url("/dokky/main.do?"+no.getBoard_id());
		no.setBoard_id(member_id);
		no.setNoti_subject("title");
		
		Map map = new HashMap();
		
		map.put("list", no);
		map.put("noti", "1");
		
		
		list.add(0, map);
		list.add(1, no);

		return list;
	}

	public String deleteAlram() {

		return null;
	}

}
