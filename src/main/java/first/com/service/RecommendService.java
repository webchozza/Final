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
	public List<Map<String, Object>> recommendList(Map<String, Object> map) {

		List<Map<String, Object>> alllist = sqlSessionTemplate.selectList("recommend.alllist");
		List<Map<String, Object>> countlist = new ArrayList<Map<String, Object>>();

		int num = 0;
		//�ΰ��� ���ؼ� member_id�� �ٸ��� board_id�� �Ȱ��� ������ ��󳻼� ������ ���� ���� �ֵ��� ã�´�aa
		for(int i=0;i<alllist.size();i++){
			for(Map.Entry<String, Object> entry : alllist.get(i).entrySet()){
				if(!String.valueOf(entry.getValue()).equals(String.valueOf(map.get("member_id")))){
					map.put("compare_member_id", entry.getValue());
					Map<String, Object> compare = sqlSessionTemplate.selectOne("recommend.similarity",map);
					countlist.add(num, compare);
					num++;
					}
				}
			}
		
		List<Map<String, Object>> recommend_list = new ArrayList<Map<String, Object>>();
		while(recommend_list.isEmpty()){
			if(countlist.isEmpty()){ return recommend_list; }
			
			Map<String, Object> compare = new HashMap<String, Object>();
			
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
		//bgroup_id�� �����;� ��. scrap���̺����� ������ ���ؾ���. ���� ���絵�� ������ ������ ���� �� ���� ���絵�� ���� ȸ�����Լ� ��õ����� �������� ���� ���� ���
		//�Խñ� ���� �����;� �Ѵ�!
			recommend_list = sqlSessionTemplate.selectList("recommend.recommendlist",map);
			if(recommend_list.isEmpty()){ countlist.remove(del); }
		}
		//ajax �ۼ��� �� list�� null�� �ƴ� �� << ���ǹ� �� �ۼ�
		return recommend_list;
	}
}
