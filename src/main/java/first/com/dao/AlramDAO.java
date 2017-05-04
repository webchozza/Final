package first.com.dao;

import java.util.List;

import first.com.model.FollowDTO;
import first.com.model.NotiDTO;

public interface AlramDAO {
	
	public List<NotiDTO> notiList(int member_id);
	
	public String deleteAlram();
	
	//��� �ۼ��� ���� �ȷο��� ȸ���� �ش� �Խñ��� �ۼ��ڿ��� �˸��� �����ִ� �޼ҵ�
	public void insertCommentNoti(int board_id, int session_id, String path);

	//�� �� �ۼ��� ���� �ȷο��� ȸ���鿡�� �˸��� �����ִ� �޼ҵ�(�ۼ��� ���� insert�ǰ� �� �� ȣ���ؾ� ��)
	public void insertNewBoardNoti(int session_id, String path, int bgroup_id);
}
