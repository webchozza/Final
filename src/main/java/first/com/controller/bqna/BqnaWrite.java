package first.com.controller.bqna;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.AlramDAO;
import first.com.dao.BqnaDAO;
import first.com.model.BoardDTO;

@Controller
public class BqnaWrite {
	
	@Resource
	private BqnaDAO bqnaService;
	
	@Resource
	private AlramDAO noti;
	
	@RequestMapping(value="/bqnawriteform")
	public ModelAndView bqnaWriteForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("BoardDTO", new BoardDTO());
		mav.setViewName("QnaWriteForm");
		return mav;
	}

	@RequestMapping(value="/bqnawrite")
	public ModelAndView bqnaWrite(@ModelAttribute("BoardDTO")BoardDTO boardDTO, BindingResult result, HttpServletRequest request, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		String content = boardDTO.getBoard_content().replaceAll("\r\n", "<br />");
		String tag = boardDTO.getBoard_tag().replaceAll(" ", "");
		boardDTO.setBoard_tag(tag);
		boardDTO.setBoard_content(content);
		
		bqnaService.bqnaWrite(boardDTO);
		
		mav.addObject("boardDTO", boardDTO);
		
		mav.setViewName("redirect:bqnalist.do");
		
		return mav;
	}

}
