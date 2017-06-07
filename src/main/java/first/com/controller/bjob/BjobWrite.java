package first.com.controller.bjob;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.AlramDAO;
import first.com.dao.BjobDAO;
import first.com.dao.TagDAO;
import first.com.model.BoardDTO;

@Controller
public class BjobWrite {

	@Resource
	private BjobDAO bjobService;
	@Resource
	private TagDAO tagService;

	// noti
	@Resource
	private AlramDAO noti;
	/////
	
	@RequestMapping(value = "/bjobwriteform")
	public ModelAndView bjobWriteForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("BoardDTO", new BoardDTO());
		mav.setViewName("jobWriteForm");
		return mav;
	}

	@RequestMapping(value = "/bjobwrite")
	public ModelAndView bjobWrite(@ModelAttribute("BoardDTO") BoardDTO boardDTO, BindingResult result,
			HttpServletRequest request, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		String content = boardDTO.getBoard_content().replaceAll("\r\n", "<br />");
		boardDTO.setBoard_content(content);
		boardDTO.setBoard_tag(tagService.insertTag(boardDTO.getBoard_tag(), 3));
		bjobService.bjobWrite(boardDTO);
		// by eongoo, new board noti
		noti.insertNewBoardNoti(boardDTO.getMember_id(), "/bjobdetail", 3);
		//

		mav.addObject("boardDTO", boardDTO);

		mav.setViewName("redirect:bjoblist.do");

		return mav;
	}

}