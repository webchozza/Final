package first.com.controller.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.MemberDAO;
import first.com.model.MemberDTO;

@Controller
public class Login {
	
	
	@Resource
	private MemberDAO memberService;
	
	ModelAndView mav = new ModelAndView();
	// LoginForm���� �̵� 
	@RequestMapping("/loginform.do") 
	public String loginForm(){
		
		return "LoginForm";
	}
	
	//�α���
	@RequestMapping("/login.do") 
	public ModelAndView login(HttpServletRequest request,MemberDTO member){
		//DB���� data�� ������ MemberDTO�� �� ����
		MemberDTO result = memberService.login(member);
		
		//���� �����Ѵٸ� session ������ �����Ѵ�.
		if(result!=null){
				
			HttpSession session = request.getSession();
			
			memberService.loginUpdate(member);
			
			session.setAttribute("member_email", result.getMember_email());
			session.setAttribute("member_name", result.getMember_name());
			session.setAttribute("member_id", result.getMember_id());
			
			mav.setViewName("Main");
			
			return mav;
		}	//���� �߻��� ��� �̵�
			mav.setViewName("LoginError");
			return mav;
	}
	//Logout
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletRequest request,MemberDTO member){
		
		HttpSession session = request.getSession(false);
		//�� false�� ����???
		if(session!=null){
			//������ ���� ���� ����
			session.invalidate();
		}
		//���ο� ��ü �����Ͽ� ������ ��ü�� ������ �� delete
		mav.addObject("member",new MemberDTO());
		// MainForm���� �̵�
		mav.setViewName("Main");
		
		return mav;
	}
	
	

}









