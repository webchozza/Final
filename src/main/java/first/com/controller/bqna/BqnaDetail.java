package first.com.controller.bqna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BqnaDAO;
import first.com.dao.RecommendDAO;
import first.com.dao.ScrapDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

@Controller
public class BqnaDetail {

	@Resource
	private BqnaDAO bqnaService;
	
	@Resource
	private ScrapDAO Scrap;
	
	@Resource 
	private RecommendDAO recommendSerivce;

	@RequestMapping(value = "/bqnadetail")
	public ModelAndView bqnaDetail(@RequestParam(value = "currentPage", defaultValue="1") int currentPage,
			@RequestParam("board_id") int board_id,
			@RequestParam(value = "session_id", defaultValue = "-1") int session_id) {

		ModelAndView mav = new ModelAndView();

		BoardDTO bqnaDetail = bqnaService.bqnaDetail(board_id);
		List<BcommentDTO> bcqnaList = bqnaService.bcqnaList(board_id);
		bqnaService.bqnaUpdateHit(board_id);

		int answerCheck = bqnaService.bqnaAnswerCheck(board_id);
		if(answerCheck > 0){
			mav.addObject("answerCheck", 1);
		}else{
			mav.addObject("answerCheck", -1);
		}
		
		for(int i=0;i<bcqnaList.size(); i++){
			if(bcqnaList.get(i).getBcomment_id() == bcqnaList.get(i).getAnswer()){
				System.out.println(bcqnaList.get(i).getAnswer_id());
				mav.addObject("answercomment", bcqnaList.get(i));
				break;
			}
		}
		

		mav.addObject("board_tag", bqnaDetail.getBoard_tag());
		mav.addObject("currentPage", currentPage);
		mav.addObject("detail", bqnaDetail);
		mav.addObject("bcqnaList", bcqnaList);
		mav.setViewName("QnaDetail");
			
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

		return mav;
	}

}
