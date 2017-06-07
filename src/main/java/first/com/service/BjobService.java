package first.com.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.controller.bjob.BjobListDTO;
import first.com.dao.BjobDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

@Service
public class BjobService implements BjobDAO {

	@Resource
	private SqlSessionTemplate SqlSessionTemplate;

	@Override
	public List<BoardDTO> bjobList(BjobListDTO bjobListDTO) {// 由ъ뒪�듃
		return SqlSessionTemplate.selectList("bjob.bjobList", bjobListDTO);
	}

	@Override
	public BoardDTO bjobDetail(int board_id) {// �긽�꽭
		return SqlSessionTemplate.selectOne("bjob.bjobSelect", board_id);
	}

	@Override
	public List<BcommentDTO> bcjobList(int board_id) {// 肄붾찘�듃由ъ뒪�듃
		return SqlSessionTemplate.selectList("bjob.bcjobList", board_id);
	}

	@Override
	public String bjobWriteForm() {// �벐湲고뤌
		return null;
	}

	@Override
	public int bjobWrite(BoardDTO boardDTO) {// �벐湲�
		return SqlSessionTemplate.insert("bjob.bjobWrite", boardDTO);
	}

	@Override
	public BoardDTO bjobModifyForm(BoardDTO boardDTO) {// �닔�젙�뤌
		return boardDTO;
	}

	@Override
	public int bjobModify(BoardDTO boardDTO) {// �닔�젙
		return SqlSessionTemplate.update("bjob.bjobModify", boardDTO);
	}

	@Override
	public int bjobDelete(int board_id) {// �궘�젣
		return SqlSessionTemplate.delete("bjob.bjobDelete", board_id);
	}

	@Override
	public int bjobHit(int board_id) {// 議고쉶�닔�뾽�럠
		return SqlSessionTemplate.update("bjob.bjobUpdateHit", board_id);
	}

	@Override
	public int bjobLike(int board_id) {// 異붿쿇
		return SqlSessionTemplate.update("bjob.bjobUpdateLike", board_id);
	}

	@Override
	public int bjobBad(int board_id) {// �떆�윭�슂
		return SqlSessionTemplate.update("bjob.bjobUpdateBad", board_id);
	}

	@Override
	public int bCjobWrite(BcommentDTO bcommentDTO) {// 肄붾찘�듃�벐湲�
		return SqlSessionTemplate.insert("bjob.bcjobWrite", bcommentDTO);
	}

	@Override
	public int bCjobDelete(int bcomment_id) {// 肄붾찘�궘�젣
		return SqlSessionTemplate.delete("bjob.bcjobDelete", bcomment_id);
	}

	@Override
	public int bjobUpdateCountco(int board_id) {// 肄붾찘�닔�뾽�럠
		return SqlSessionTemplate.update("bjob.bjobUpdateCountco", board_id);
	}

}
