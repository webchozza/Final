package first.com.controller.bjob;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BjobDAO;
import first.com.model.BoardDTO;

@Controller
public class BjobList {

	@Resource
	private BjobDAO bjobService;

	private int n;
	private String search;

	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockPage = 5;
	private String pagingHtml;
	private BjobPaging page;
	private String sort;
	
	private BjobListDTO bjobListVO= new BjobListDTO(); 

	@RequestMapping(value = "/bjoblist")
	public ModelAndView bjobList(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<BoardDTO> boardDTO = null;

		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		if (request.getParameter("n") == null || request.getParameter("n").trim().isEmpty()
				|| request.getParameter("n").equals("0")) {
			n = 0;
		} else {
			n = Integer.parseInt(request.getParameter("n"));
		}

		if (request.getParameter("search") == null || request.getParameter("search").trim().isEmpty()) {
			search = "";
		} else {
			search = request.getParameter("search");
		}

		if (request.getParameter("sort") == null || request.getParameter("sort").trim().isEmpty()) {
			sort = "";
		} else {
			sort = request.getParameter("sort");
		}

		bjobListVO.setSort(sort);
		bjobListVO.setN(n);
		bjobListVO.setSearch(search);
		boardDTO = bjobService.bjobList(bjobListVO);
		
		totalCount = boardDTO.size();
		
		page = new BjobPaging("bjoblist", currentPage, totalCount, blockCount, blockPage, search, n, sort);
		pagingHtml = page.getPagingHtml().toString();
		int lastCount = totalCount;
		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;
		
		boardDTO = boardDTO.subList(page.getStartCount(), lastCount);
		
		mav.addObject("search", search);
		mav.addObject("n", n);
		mav.addObject("totalCount", totalCount);
		mav.addObject("pagingHtml", pagingHtml);
		mav.addObject("currentPage", currentPage);
		mav.addObject("sort", sort);
		mav.addObject("bjoblist", boardDTO);

		if(request.getParameter("ap") != null){
			mav.setViewName("job/jobList");
		}else{
			mav.setViewName("jobList");
		}
		return mav;

	}

}
