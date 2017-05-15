package first.com.service;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.NotiCountDAO;

@Service
@Resource(name="noticount")
public class NotiCount implements NotiCountDAO{
	
	HttpSession session;
	
	int count;
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
		
	public int getNotiCount(int noti_count) {//�ֱ������� ������ �� ������ ���ο� �˸��� ����� �̾ƿ��� �޼ҵ�(�ǽð����� �˸������� üũ�ؼ� �޴��� �����)
		
		return noti_count;
	}

	public void setNotiCount(int member_id,  int noti_count) {//���ο� �˸��� ���涧 ī��Ʈ�� �����ִ� �޼ҵ�

		String id = String.valueOf(member_id);
		System.out.println(id+"���ο� �˸��̴�");
		System.out.println(noti_count);
		int a = noti_count+1;
		session.setAttribute(id,a);
	}
	
	public void initCount(int member_id){//�˸������ �� �� �޴��� �����ִ� ���ο� �޽��� ī��Ʈ�� �ʱ�ȭ �����ִ� �޼ҵ�
		
		String id = String.valueOf(member_id);
		session.removeAttribute(id);
		
		}

	@Override
	public int login_noti_count(int member_id) {
		return sqlSessionTemplate.selectOne("noti.noti_count",  member_id);
	}

	@Override
	public void logout_noti_count(Map<String, Object> map) {
		sqlSessionTemplate.update("noti.insert_noti_count", map);
	}
}
