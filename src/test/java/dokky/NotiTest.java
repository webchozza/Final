package dokky;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@TransactionConfiguration
@Transactional
//��� �ۼ��� �˸��Խñۿ� ���� �μ�Ʈ �ϴ� �����׽�Ʈ
public class NotiTest {
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void testAlramList() throws Exception {
		mockMvc.perform(get("/test.do").param("board_id", "2").param("session_id", "1000").param("board_url", "/test.list").param("kinds", "comment"))
			.andExpect(status().isOk());
		//MockHttpServletRequestBuilder�� ��� ��û ���� ���� �޼ҵ�� MockHttpServletRequestBuilder�� �����ϱ� ������ �޼�Ʈ ü�̴� ������� ��û ������ ������ �� �ִ�.
	}

}
