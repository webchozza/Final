package first.com.controller.bjob;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BjobDAO;

@Controller
public class BCjobDelete {
	
	@Resource
	private BjobDAO bjobService;
	
	@RequestMapping("/bjobdeletecomment")
	public ModelAndView bCjobDelete(HttpServletRequest request,@RequestParam("session_id") int session_id) {
		
		ModelAndView mav= new ModelAndView();
		int bcomment_id= Integer.parseInt(request.getParameter("bcomment_id"));
		bjobService.bCjobDelete(bcomment_id);
		bjobService.bjobUpdateCountco(Integer.parseInt(request.getParameter("board_id")));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", request.getParameter("board_id"));
		map.put("currentPage", request.getParameter("currentPage"));
		map.put("session_id", request.getParameter("session_id"));
		
		mav.addAllObjects(map);
		mav.setViewName("redirect:bjobdetail.do?session_id="+session_id);

		return mav;
	}
}
