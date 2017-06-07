package first.com.controller.bjob;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BjobDAO;

@Controller
public class BjobDelete {

	@Resource
	private BjobDAO bjobService;
	
	@RequestMapping("/bjobdelete")
	public ModelAndView bjobDelete(HttpServletRequest request) {
		
		ModelAndView mav= new ModelAndView();
		int board_id= Integer.parseInt(request.getParameter("board_id"));
		bjobService.bjobDelete(board_id);
		mav.setViewName("redirect:/bjoblist.do");

		return mav;
	}

}
