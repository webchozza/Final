package first.com.controller.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import first.com.dao.AlramDAO;
import first.com.dao.NotiCountDAO;
import first.com.model.NotiDTO;

@Controller
public class Alram {

	@Resource
	private AlramDAO noti;
	
	@Resource
	private NotiCountDAO noticount;

	@RequestMapping(value = "/notilist.do")
	@ResponseBody
	public List<NotiDTO> alramList(@RequestParam(value = "session_id", required=false) int member_id, Model model) {

		//Notification���̺� �ִ� ���� �� ���� ���� ���� ȸ���� session_id�� �̿��Ͽ� ��ġ�ϴ� �������� �����´�.
		//NotiDTO�� ���� �־ �����ϱ� ������ json��ü�� Ű�� DTO�� �� property�̴�.
		List<NotiDTO> list = noti.notiList(member_id);
		
		noticount.initCount(member_id);//�˸��� Ȯ���� �� ���ο� �˸��� �˷��ִ� ī��Ʈ�� �ִٸ� �ʱ�ȭ
		
		return list;
	}
	
	@RequestMapping(value="/notiDelete.do")
	public String notiDelete() {

		return null;
	}
	
	@RequestMapping(value="/notiCount.do")
	@ResponseBody
	public int notiCount(@RequestParam(value="session_id") int member_id){
		
		return noticount.getNotiCount(member_id);
	}

}
