package first.com.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.AlramDAO;
import first.com.dao.NotiCountDAO;
import first.com.model.BoardDTO;
import first.com.model.FollowDTO;
import first.com.model.NotiDTO;

@Service
@Resource(name="noti")
public class AlramService implements AlramDAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Resource
	private NotiCountDAO noticount;

	@Override
	public List<NotiDTO> notiList(int member_id) {
		List<NotiDTO> list = sqlSessionTemplate.selectList("noti.list", member_id);
		return list;
	}

	@Override
	public int deleteAlram(int noti_id) {
		return sqlSessionTemplate.delete("noti.delete", noti_id);
	}
	
	//��� �ۼ��� ���� �ȷο��� ȸ���� �ش� �Խñ��� �ۼ��ڿ��� �˸��� �����ִ� �޼ҵ�
	public void insertCommentNoti(int board_id, int session_id, String path){//board_id = ��� �ۼ��� �Խñ��� board_id
																			 //session_id = �������� ȸ���� session_id
																		     //board_url = �󼼺����� url�� ex) /dokky/bfreedetail.do => "bfreedetail"
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_id", session_id);//����� �ۼ��� ����� id
		
		BoardDTO board = (BoardDTO)sqlSessionTemplate.selectOne("noti.select_board", board_id);
		List<FollowDTO> followComment = sqlSessionTemplate.selectList("noti.follower_member_id", map);
		
		//�˸�â���� �ش� �Խñ۷� �ٷ� �̵��� �� �ֵ��� url ����
		path = "/dokky"+path+".do?board_id="+board_id;

		map.put("board_title", board.getBoard_title());//�Խñ� ����
		map.put("board_url", path);//�Խñ� �ּ�
		map.put("bgroup_id", board.getBgroup_id());//�Խ��� ����
		map.put("board_id", board_id);//�Խñ��� pk
		
		if(followComment != null){//���� �ۼ��� ����� �ȷο��� ȸ���� �ִٸ�
			for(int i=0; i < followComment.size(); i++){
				map.put("member_id", followComment.get(i).getMember_id());//����ۼ��ڸ� �ȷο��� ȸ���� ���̵� map��ü�� ����
				map.put("noti_kinds", "follow_comment");//�ȷο��� ȸ���� ����� �ۼ����� ��
				noticount.setNotiCount(followComment.get(i).getMember_id());//�� �˸��� �� ��ü�� �����Ѵ�.
				sqlSessionTemplate.insert("noti.insert", map);//�ۼ��ڸ� �ȷο��� ȸ���� �˸����̺� �˸������� �־��ش�.
			}
		}
	
		map.put("member_id", board.getMember_id());//����� �ۼ��� �Խñ��� �ۼ��� id(���� ���� �̸��� key�� ���� map��ü�� �ִٸ� �������)
		map.put("noti_kinds", "comment"); //���� �Խñۿ� ����� �޷��� ��
		noticount.setNotiCount(board.getMember_id());
		sqlSessionTemplate.insert("noti.insert", map);
		
	}
	
	//�� �� �ۼ��� ���� �ȷο��� ȸ���鿡�� �˸��� �����ִ� �޼ҵ�(�ۼ��� ���� insert�ǰ� �� �� ȣ���ؾ� ��)
	public void insertNewBoardNoti(int session_id, String path, int bgroup_id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_id", session_id);//�� ���� �ۼ��� ����� id
		map.put("bgroup_id", bgroup_id);//�Խ��� ����

		BoardDTO board = (BoardDTO)sqlSessionTemplate.selectOne("noti.New_Board_select_board", map);

		List<FollowDTO> followNewBoard = sqlSessionTemplate.selectList("noti.follower_member_id", session_id);
		
		path = "/dokky"+path+".do?board_id="+board.getBoard_id();
		
		map.put("board_title", board.getBoard_title());//�Խñ� ����
		map.put("board_url", path);//�Խñ� �ּ�
		map.put("board_id", board.getBoard_id());//�Խñ��� pk
		
		if(followNewBoard != null){//���� �ۼ��� ����� �ȷο��� ȸ���� �ִٸ�
			for(int i=0; i < followNewBoard.size(); i++){
				map.put("member_id", followNewBoard.get(i).getMember_id());
				map.put("noti_kinds", "follow_NewBoard");
				noticount.setNotiCount(followNewBoard.get(i).getMember_id());
				sqlSessionTemplate.insert("noti.insert", map);//�ۼ��ڸ� �ȷο��� ȸ���� �˸����̺� �˸������� �־��ش�.
			}
		}
	}
}
		
