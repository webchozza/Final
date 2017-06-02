package first.com.controller.bqna;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.AlramDAO;
import first.com.dao.BqnaDAO;
import first.com.model.BcommentDTO;

@Controller
public class BCqnaWrite {
	
	@Resource
	private BqnaDAO bqnaService;
	
	@Resource
	private AlramDAO noti;
	
	@RequestMapping(value = "/bqnawritecomment")
	public ModelAndView bCfreeWrite(@ModelAttribute("BcommentDTO") BcommentDTO bcommentDTO, BindingResult result,
			HttpServletRequest request, HttpSession session,@RequestParam("session_id") int session_id) {
		ModelAndView mav = new ModelAndView();
		
		String content= bcommentDTO.getBcomment_content().replaceAll("\r\n", "<br />");
		bcommentDTO.setBcomment_content(content);
		
		bqnaService.bCqnaWrite(bcommentDTO);
		bqnaService.bqnaUpdateCountco(bcommentDTO.getBoard_id());
		mav.addObject("bcommentDTO", bcommentDTO);
		
		//by eongoo, comment noti
		noti.insertCommentNoti(bcommentDTO.getBoard_id(), bcommentDTO.getMember_id(), "/bqnadetail");
		//
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", bcommentDTO.getBoard_id());
		map.put("currentPage", request.getParameter("currentPage"));
		map.put("session_id", bcommentDTO.getMember_id());

		mav.addAllObjects(map);
		mav.setViewName("redirect:bqnadetail.do?session_id"+session_id);

		return mav;
	}

}
