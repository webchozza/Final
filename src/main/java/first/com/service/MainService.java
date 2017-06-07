package first.com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.MainDAO;
import first.com.model.BoardDTO;

@Service
@Resource(name="mainSearch")
public class MainService implements MainDAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;


	@Override
	public Map<String, List<Map<String, Object>>> main() {
		Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
		
		List<Map<String, Object>> oslist = sqlSessionTemplate.selectList("main.oslist");
		List<Map<String, Object>> colist = sqlSessionTemplate.selectList("main.colist");
		List<Map<String, Object>> qnlist = sqlSessionTemplate.selectList("main.qnlist");
		List<Map<String, Object>> jolist = sqlSessionTemplate.selectList("main.jolist");
		
		map.put("oslist", oslist);
		map.put("colist", colist);
		map.put("qnlist", qnlist);
		map.put("jolist", jolist);
		
		return map;
	}

	@Override
	public int allBordCount(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("main.allBoardCount", map);
	}

	@Override
	public List<BoardDTO> allSearch(Map<String,Object> map) {
		return sqlSessionTemplate.selectList("main.allSearchList", map);
	}
	
	
	

}
