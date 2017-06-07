package first.com.controller.bjob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BjobDAO;
import first.com.dao.RecommendDAO;
import first.com.dao.ScrapDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

@Controller
public class BjobDetail {

	@Resource
	private BjobDAO bjobService;
	
	@Resource 
	private ScrapDAO Scrap;
	@Resource 
	private RecommendDAO recommendSerivce;

	
	@RequestMapping("/bjobdetail")
	public ModelAndView bjobDetail(@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="board_id") int board_id,
			@RequestParam(value="session_id", defaultValue="-1") int session_id) {

		ModelAndView mav = new ModelAndView();
		
		BoardDTO bjobDetail = bjobService.bjobDetail(board_id);		
		List<BcommentDTO> bcjobList = bjobService.bcjobList(board_id);
		bjobService.bjobHit(board_id);
		
		mav.addObject("board_tag", bjobDetail.getBoard_tag());
		mav.addObject("currentPage", currentPage);
		mav.addObject("bjobDetail", bjobDetail);
		mav.addObject("bcjobList", bcjobList);
		mav.setViewName("jobDetail");
		
		//add by eongoo
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_id", session_id);
		map.put("board_id", board_id);
		if(session_id == -1){ 
			mav.addObject("scrapCheck", "-1");
			mav.addObject("recommendCheck", "-1");
		} else {
			mav.addObject("scrapCheck", Scrap.scrapCheck(map));
			mav.addObject("recommendCheck", recommendSerivce.recommendCheck(map));
		}
		/////
		
		return mav;
	}

}
