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
import first.com.dao.RecommendDAO;

@Controller
public class BjobRecommend {

	@Resource
	private BjobDAO bjobService;
	@Resource 
	private RecommendDAO recommendSerivce;

	@RequestMapping("/bjobrecommend")
	public ModelAndView bjobRecommend(HttpServletRequest request, @RequestParam(value="session_id") int session_id) {
		
		ModelAndView mav = new ModelAndView();
		
		int currentPage= Integer.parseInt(request.getParameter("currentPage"));
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		bjobService.bjobLike(board_id);
		
		//by eongoo, recommend
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", session_id);
		map.put("board_id", board_id);
		recommendSerivce.addRecommend(map);
		//
		
		mav.setViewName("redirect:bjobdetail.do?board_id=" + request.getParameter("board_id") + "&currentPage=" 
						 + request.getParameter("currentPage") + "&session_id=" + session_id);//session_id parameter add by eongoo

		return mav;
	}

}
