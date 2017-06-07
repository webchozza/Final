package first.com.controller.bfree;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BfreeDAO;
import first.com.model.BoardDTO;

@Controller
public class BfreeList {

	@Resource
	private BfreeDAO bfreeService;

	private int n;
	private String search;

	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockPage = 5;
	private String pagingHtml;
	private BfreePaging page;
	private String sort;
	
	private BfreeListDTO bfreeListVO= new BfreeListDTO(); 

	@RequestMapping(value = "/bfreelist")
	public ModelAndView bfreeList(HttpServletRequest request) throws Exception {
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

		bfreeListVO.setSort(sort);
		bfreeListVO.setN(n);
		bfreeListVO.setSearch(search);
		boardDTO = bfreeService.bfreeList(bfreeListVO);
		
		totalCount = boardDTO.size();
		
		page = new BfreePaging("bfreelist", currentPage, totalCount, blockCount, blockPage, search, n, sort);
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
		mav.addObject("bfreelist", boardDTO);

		if(request.getParameter("ap") != null){
			mav.setViewName("free/FreeList");
		}else{
			mav.setViewName("FreeList");
		}
		return mav;

	}

}
