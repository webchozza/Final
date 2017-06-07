package first.com.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.AdminDAO;
import first.com.model.MemberDTO;

@Controller
public class AdminModify {
	
	@Resource
	private AdminDAO admin;
	
	//(관리자) 회원 정보 수정 폼 이동
	@RequestMapping("/AdminModifyForm.do")
	public String adminModifyForm(@RequestParam(value="member_id") int member_id, Model model){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("member_id", member_id);
				
		MemberDTO member = admin.memberSelect(map);
		
		model.addAttribute("member", member);
		
		return "admin/AdminModifyForm";
	}
	
	//(관리자) 회원 정보 수정 처리
	@RequestMapping("/MemberModify.do")
	public ModelAndView adminModify(@RequestParam(value="ap") String ap, MemberDTO member) {
		ModelAndView mv = new ModelAndView();
		
		admin.memberModify(member);
		
		mv.setViewName("redirect:/MemberList.do?ap="+ap);

		return mv;
	}

}
