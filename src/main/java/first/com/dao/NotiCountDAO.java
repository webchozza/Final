package first.com.dao;

public interface NotiCountDAO {
	
	//�ֱ������� ������ �� ������ ���ο� �˸��� ����� �̾ƿ��� �޼ҵ�(�ǽð����� �˸������� üũ�ؼ� �޴��� �����)
	public int getNotiCount(int member_id);
	
	//���ο� �˸��� ���涧 ī��Ʈ�� �����ִ� �޼ҵ�
	public void setNotiCount(int member_id);
	
	//�˸������ �� �� �޴��� �����ִ� ���ο� �޽��� ī��Ʈ�� �ʱ�ȭ �����ִ� �޼ҵ�
	public void initCount(int member_id);
}
