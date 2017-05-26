package first.com.controller.bfree;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BfreeDAO;
import first.com.dao.TagDAO;
import first.com.model.BoardDTO;


@Controller
public class BfreeModify {
	
	@Resource
	private BfreeDAO bfreeService;
	@Resource
	private TagDAO tagService;

	@RequestMapping(value="/bfreemodifyform")
	public ModelAndView bfreeModifyForm(int board_id, HttpServletRequest request) {
		ModelAndView mav= new ModelAndView();

		BoardDTO boardDTO= bfreeService.bfreeDetail(board_id);
		
		String content= boardDTO.getBoard_content().replaceAll("<br />", "\r\n");
		boardDTO.setBoard_content(content);
		boardDTO.setBoard_tag(tagService.modifyFormView(boardDTO.getBoard_tag(), 2));//bgroup_id=2
		mav.addObject("currentPage", request.getParameter("currentPage"));
		mav.addObject("boardDTO", boardDTO);
		mav.setViewName("FreeModifyForm");
		
		return mav;
	}
	
	@RequestMapping(value="/bfreemodify")
	public ModelAndView bfreeModify(BoardDTO boardDTO, HttpServletRequest request,@RequestParam("session_id") int session_id) {
		ModelAndView mav= new ModelAndView();

		String content= boardDTO.getBoard_content().replaceAll("\r\n", "<br />");
		boardDTO.setBoard_content(content);
		boardDTO.setBoard_tag(tagService.insertTag(boardDTO.getBoard_tag(), 2));//bgroup_id=2
		
		bfreeService.bfreeModify(boardDTO);
		mav.setViewName("redirect:bfreedetail.do?board_id="+boardDTO.getBoard_id()+"&currentPage="+request.getParameter("currentPage")+"&session_id="+session_id);
		return mav;
	}


}