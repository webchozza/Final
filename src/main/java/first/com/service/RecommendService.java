package first.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.RecommendDAO;

@Service
@Resource(name="recommendSerivce")
public class RecommendService implements RecommendDAO {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;


	@Override
	public void addRecommend(Map<String, Object> map) {
		sqlSessionTemplate.insert("recommend.insert",map);
	}

	@Override
	public int recommendCheck(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("recommend.check", map);
	}

	
	//recommend board
	@Override
	public List<HashMap<String, Object>> recommendList(Map<String, Object> map) {

		//���絵 ���� ���� ����Ʈ ����
		List<HashMap<String, Object>> countlist = new ArrayList<HashMap<String, Object>>();
		
		//��õ �� ��ũ�� ���̺� �����ϴ� ȸ������ id���� �����´�.
		List<HashMap<String, Object>> alllist = sqlSessionTemplate.selectList("recommend.alllist");

		int num = 0;
		for(int i=0;i<alllist.size();i++){
			for(HashMap.Entry<String, Object> entry : alllist.get(i).entrySet()){
				if(!String.valueOf(entry.getValue()).equals(String.valueOf(map.get("member_id")))){
					map.put("compare_member_id", entry.getValue());
					
					//������ ������ id(�������� ȸ���� id�� ����)�� �̿��� ���� ���� ȸ������ (��õ �� ��ũ�� ��������)������ ���Ͽ� ���絵�� ���Ѵ�.
					HashMap<String, Object> compare = sqlSessionTemplate.selectOne("recommend.similarity",map);
					if(compare.containsKey("SIMILARITY")){
						countlist.add(num, compare);
						num++;
					}
				}
			}
		}

		//��õ ���� ���� ����Ʈ ��ü ����
		List<HashMap<String, Object>> recommend_list = new ArrayList<HashMap<String, Object>>();
		
		//���絵�� ���� ���� ȸ�����Լ� ��õ �Խñ��� �̾ƿ��� �ݺ��� ����
		while(recommend_list.isEmpty()){
			
			//���� ���̻� ���絵�� �ٸ� ȸ���� �������� �ʴ´ٸ� �� ����Ʈ ��ȯ
			if(countlist.isEmpty()){ return recommend_list; }
			
			HashMap<String, Object> compare = new HashMap<String, Object>();
			
			double similarity = 0;
			int del = 0;
			for(int i=0; i<countlist.size();i++){
				if(similarity != 0){
					if(similarity < Double.parseDouble(String.valueOf(countlist.get(i).get("SIMILARITY")))){
						similarity = Double.parseDouble(String.valueOf(countlist.get(i).get("SIMILARITY")));
						compare = countlist.get(i);
						del = i;
					}else{
						continue;
					}
				}else if(similarity == 0){ 
					similarity = Double.parseDouble(String.valueOf(countlist.get(i).get("SIMILARITY"))); 
					compare = countlist.get(i);
					del = i;
				}
			}

			//���� ȸ���� ��õ �� ��ũ���� ���Ͽ� ������ ���� ������ ��� (����ȸ���� ��õ�̳� ��ũ������ ����� �����ش�)
			map.put("compare_member_id", compare.get("MEMBER_ID"));

			//�ڻ��� ���絵�� 1�� ���� ����� ȸ�����Լ� ��õ ���� �̾ƿ´�.
			recommend_list = sqlSessionTemplate.selectList("recommend.recommendlist",map);

			//���� �ڻ��� ���絵���� 1�̶��(��õ, ��ũ���� �Խñ��� ������ �����ϴٸ�(recommend_list�� ���� ������ ����.) ����Ʈ���� �ش� ���絵�� ȸ���� ����
			//��, ���� �ݺ��� 2��°�� ���� ���絵�� ���� ȸ�����Լ� ��õ ���� �������� ���ؼ�.
			if(recommend_list.isEmpty()){ countlist.remove(del); }
		}

		//�̾ƿ� ��õ �Խñ� ��� Ȥ�� (���絵�� ���� 1�̶��) �� ����Ʈ ��ü ��ȯ
		return recommend_list;
	}

	//�˻��� �˻�� �������� ���絵�� ���� ��õ �� ��������
	@Override
	public List<HashMap<String, Object>> recommendSearch(List<String> list) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search_list", list);

		//�˻�� ���� �� �Խñ��� �󵵼��� ���ͷ� ���� ���� ����Ʈ
		List<HashMap<String, Object>> searchlist = sqlSessionTemplate.selectList("recommend.recommendsearch",map);
		//�� �Խñ��� ���Ͽ� ���絵�� ���� ������ board_id 6���� ������ ����Ʈ
		List<HashMap<String, Object>> comparelist = new ArrayList<HashMap<String, Object>>();
		//�� ����Ʈ�� board_id�� �信�� ������ �������� ������ ���� ����Ʈ
		List<HashMap<String, Object>> recommendlist = new ArrayList<HashMap<String, Object>>();

		map.put("searchlist", searchlist);
		System.out.println(searchlist);
		if(!searchlist.isEmpty()){
		comparelist = sqlSessionTemplate.selectList("recommend.similaritysearch", map);
		}
		System.out.println(comparelist);
		if(comparelist.isEmpty()){
			recommendlist = sqlSessionTemplate.selectList("recommend.basiclist", map);
		}else if(!comparelist.isEmpty()){
			map.put("recosearchboard", comparelist);
			recommendlist = sqlSessionTemplate.selectList("recommend.recosearchboard", map);
		}

		return recommendlist;
	}
	
}
