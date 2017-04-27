package first.com.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoggerInterceptor extends HandlerInterceptorAdapter {
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		//session member_id�� ���� 
		String member_id = (String)session.getAttribute("member_id");
		System.out.println("���ͼ��� :1");
		/*�� �α��� �� writeform, memberlist, recommend �� �̵� �Ұ�  ����, main.do�� �̵�*/
		if(member_id==null){ 
			System.out.println("���ͼ��� :2");
			if(request.getRequestURI().contains("writeform.do")||
			 request.getRequestURI().contains("memberlist.do")||
			 request.getRequestURI().contains("recommand.do")){
				response.sendRedirect("/dokky/loginform.do");
				return false;
			}else{
				System.out.println("���ͼ��� :3");
				return true;
			}
		}else{
			if(member_id.equals("admin")){
				System.out.println("���ͼ��� :4");
				return true;
			}else if(request.getRequestURI().contains("memberlist.do")){
				System.out.println("���ͼ��� :5");
				
				return false;
			}
			System.out.println("���ͼ��� :6");
				return true;
				
		}
		
	}
	
}
