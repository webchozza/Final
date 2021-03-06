package first.com.controller.bfree;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.AlramDAO;
import first.com.dao.BfreeDAO;
import first.com.dao.TagDAO;
import first.com.model.BoardDTO;

@Controller
public class BfreeWrite {

	@Resource
	private BfreeDAO bfreeService;
	@Resource
	private TagDAO tagService;

	// noti
	@Resource
	private AlramDAO noti;
	/////
	
	@RequestMapping(value = "/bfreewriteform")
	public ModelAndView bfreeWriteForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("BoardDTO", new BoardDTO());
		mav.setViewName("FreeWriteForm");
		return mav;
	}

	@RequestMapping(value = "/bfreewrite")
	public ModelAndView bfreeWrite(@ModelAttribute("BoardDTO") BoardDTO boardDTO, BindingResult result,
			HttpServletRequest request, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		String content = boardDTO.getBoard_content().replaceAll("\r\n", "<br />");
		boardDTO.setBoard_content(content);
		boardDTO.setBoard_tag(tagService.insertTag(boardDTO.getBoard_tag(), 2));//bgroup_id=2
		bfreeService.bfreeWrite(boardDTO);
		// by eongoo, new board noti
		noti.insertNewBoardNoti(boardDTO.getMember_id(), "/bfreedetail", 2);
		//

		mav.addObject("boardDTO", boardDTO);

		mav.setViewName("redirect:bfreelist.do");

		return mav;
	}

}