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
					System.out.println(compare);
					}
				}
			}
		System.out.println(countlist);
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
					System.out.println(similarity+"������ ȸ���� ");
					compare = countlist.get(i);
					System.out.println(compare+"�����");
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
		
		List<HashMap<String, Object>> searchlist = sqlSessionTemplate.selectList("recommend.recommendsearch",map);
		System.out.println(searchlist);
		
		
		List similaritylist = new ArrayList();
		Map<String ,Object> putmap = null;
		for(int i=0;i<searchlist.size();i++){
			putmap = new HashMap<String, Object>();
			putmap.put("board_id", searchlist.get(i).get("BOARD_ID"));
			putmap.put("vector_name",searchlist.get(i).get("VECTOR_NAME"));
			putmap.put("vector_title", searchlist.get(i).get("VECTOR_TITLE"));
			putmap.put("vector_content", searchlist.get(i).get("VECTOR_CONTENT"));
			similaritylist.add(i, putmap);
		}
		map.put("similaritylist", similaritylist);
		
		List similaritylist2 = new ArrayList();
		Map<String ,Object> compare_putmap = null;
		for(int i=0;i<searchlist.size();i++){
			compare_putmap = new HashMap<String, Object>();
			if(i==(searchlist.size()-1)){
				compare_putmap.put("vector_name",searchlist.get(0).get("VECTOR_NAME"));
				compare_putmap.put("vector_title", searchlist.get(0).get("VECTOR_TITLE"));
				compare_putmap.put("vector_content", searchlist.get(0).get("VECTOR_CONTENT"));
			}else{
			compare_putmap.put("vector_name",searchlist.get(i+1).get("VECTOR_NAME"));
			compare_putmap.put("vector_title", searchlist.get(i+1).get("VECTOR_TITLE"));
			compare_putmap.put("vector_content", searchlist.get(i+1).get("VECTOR_CONTENT"));
			}
			similaritylist2.add(i, compare_putmap);
		}
		map.put("comparesimilaritylist", similaritylist2);
		
		List<HashMap<String, Object>> comparelist = sqlSessionTemplate.selectList("recommend.similaritysearch", map);
		System.out.println(comparelist);
		
		map.put("recosearchboard", comparelist);
		List<HashMap<String, Object>> recommendlist = sqlSessionTemplate.selectList("recommend.recosearchboard", map);
	System.out.println(recommendlist);
		return recommendlist;
	}
	
}
