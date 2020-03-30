package com.mzj.springmvc.spittr;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.mzj.springmvc.spittr.web.HomeController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class HomeControllerTest {

  @Test
  public void testHomePage() throws Exception {
    HomeController controller = new HomeController();

    /**
     * 从Spring 3.2开始，我们可以按照控制器的方式来测试Spring MVC 中的控制器了，
     * Spring现在包含了一 种mock Spring MVC并针对控制器执行HTTP请求的机制
     * 这样的话， 在测试控制器的时候，就没有必要再启动Web服务器和Web浏览器了:
     *
     */
    MockMvc mockMvc = standaloneSetup(controller).build();
    mockMvc.perform(get("/"))
           .andExpect(view().name("home"));//这种测试发起了对“/”的GET请求，并断言结果视图的名称为 home。
  }

}
