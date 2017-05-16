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
	
	//�� ��ü���� Ȯ������ ���� �˸��� ������ �����´�.(ajax�� �̿��ؼ� �ֱ�������)
	public int getNotiCount(int member_id) {
		
		int count = 0;
		
		if(NotiCount != null && NotiCount.containsKey(member_id)){
			for(Map.Entry<Integer, Integer> entry : this.NotiCount.entrySet()){
				if(entry.getKey() == member_id){
					System.out.println(NotiCount.get(member_id));
					count  = entry.getValue();
					System.out.println(count+"ī��Ʈ");
					break;
				}
			}
		}
			return count;
	}
	
	//�ۼ��ۿ� ����� �޸� ��, �ȷο��� ȸ���� ���� �ۼ��ϰų� ����� �ۼ��� �� �� �˸��� �� ��ü�� �����Ѵ�.
	public void setNotiCount(int member_id) {
	
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
		
		NotiCount = notiCount;
	}
	
	//�˸� ����� ��ġ�� �� ��ü�� ����� �ش� ȸ���� �� �˸� ������ �ʱ�ȭ �Ѵ�.
	public void initCount(int member_id){
		if(NotiCount != null && NotiCount.containsKey(member_id)){
			for(Map.Entry<Integer, Integer> entry : this.NotiCount.entrySet()){
				if(entry.getKey() == member_id){
					NotiCount.remove(member_id);
				}
			}
		}
	}
}

