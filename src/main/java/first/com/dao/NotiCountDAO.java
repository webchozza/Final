package first.com.dao;

public interface NotiCountDAO {
	
	public int getNotiCount(int member_id);
	
	public void setNotiCount(int member_id);
	
	//�˸������ �� �� �޴��� �����ִ� ���ο� �޽��� ī��Ʈ�� �ʱ�ȭ �����ִ� �޼ҵ�
	public void initCount(int member_id);
}
