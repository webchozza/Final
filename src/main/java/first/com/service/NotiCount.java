package first.com.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import first.com.dao.NotiCountDAO;

@Service
@Resource(name="noticount")
public class NotiCount implements NotiCountDAO{
		
	private Map<Integer, Integer> NotiCount;

	public int getNotiCount(int member_id) {//�ֱ������� ������ �� ������ ���ο� �˸��� ����� �̾ƿ��� �޼ҵ�(�ǽð����� �˸������� üũ�ؼ� �޴��� �����)
		int count = 0;
		if(NotiCount != null && NotiCount.containsKey(member_id)){
			for(Map.Entry<Integer, Integer> entry : this.NotiCount.entrySet()){
				if(entry.getKey() == member_id){
					count  = entry.getValue();
					break;
				}
			}
		}
			System.out.println(count+"get�޼ҵ�");
			return count;
	}

	public void setNotiCount(int member_id) {//���ο� �˸��� ���涧 ī��Ʈ�� �����ִ� �޼ҵ�
		int count = 1;
		
		if(NotiCount != null && NotiCount.containsKey(member_id)){
			for(Map.Entry<Integer, Integer> entry : this.NotiCount.entrySet()){
				if(entry.getKey() == member_id){
					count = entry.getValue() + 1;
					break;
				}
			}
		}
		
		Map<Integer, Integer> notiCount = new HashMap<Integer, Integer>();
		notiCount.put(member_id, count);
		System.out.println(count+"set�޼ҵ�");
		
		NotiCount = notiCount;
	}
	
	public void initCount(int member_id){//�˸������ �� �� �޴��� �����ִ� ���ο� �޽��� ī��Ʈ�� �ʱ�ȭ �����ִ� �޼ҵ�
		if(NotiCount != null && NotiCount.containsKey(member_id)){
			for(Map.Entry<Integer, Integer> entry : this.NotiCount.entrySet()){
				if(entry.getKey() == member_id){
					NotiCount.remove(member_id);
					System.out.println(NotiCount+"�ʱ�ȭ");
				}
			}
		}
	}
}
