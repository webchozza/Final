package first.com.dao;

import java.util.Map;

public interface NotiCountDAO {
	
	//�ֱ������� ������ �� ������ ���ο� �˸��� ����� �̾ƿ��� �޼ҵ�(�ǽð����� �˸������� üũ�ؼ� �޴��� �����)
	public int getNotiCount(int noti_count);
	
	//���ο� �˸��� ���涧 ī��Ʈ�� �����ִ� �޼ҵ�
	public void setNotiCount(int member_id,  int noti_count);
	
	//�˸������ �� �� �޴��� �����ִ� ���ο� �޽��� ī��Ʈ�� �ʱ�ȭ �����ִ� �޼ҵ�
	public void initCount(int member_id);
	
	public int login_noti_count(int member_id);
	
	public void logout_noti_count(Map<String, Object> map);
}
