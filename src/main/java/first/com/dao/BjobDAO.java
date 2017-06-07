package first.com.dao;

import java.util.List;

import first.com.controller.bjob.BjobListDTO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

public interface BjobDAO {
	
	public List<BoardDTO> bjobList(BjobListDTO bjobListVO);
	
	public BoardDTO bjobDetail(int board_id);
	
	public String bjobWriteForm();
	
	public int bjobWrite(BoardDTO boardDTO);
	
	public BoardDTO bjobModifyForm(BoardDTO boardDTO);
	
	public int bjobModify(BoardDTO boardDTO);
	
	public int bjobDelete(int board_id);
	
	public int bjobHit(int board_id);
	
	public int bjobLike(int board_id);
	
	public int bjobBad(int board_id);
	

	public List<BcommentDTO> bcjobList(int board_id);
	
	public int bCjobWrite(BcommentDTO bcommentDTO);
	
	public int bCjobDelete(int bcomment_id);
	
	public int bjobUpdateCountco(int board_id);

	

}
