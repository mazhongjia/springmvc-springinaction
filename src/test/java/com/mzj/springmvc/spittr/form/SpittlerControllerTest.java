package com.mzj.springmvc.spittr.form;

import com.mzj.springmvc.spittr.Spitter;
import com.mzj.springmvc.spittr.Spittle;
import com.mzj.springmvc.spittr.data.SpitterRepository;
import com.mzj.springmvc.spittr.data.SpittleRepository;
import com.mzj.springmvc.spittr.web.form.SpitterController;
import com.mzj.springmvc.spittr.web.restful.SpittleController3;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpittlerControllerTest {

  /**
   * 测试点击注册按钮发送的请求，跳转到表单输入页面
   * @throws Exception
   */
  @Test
  public void shouldShowRegistration() throws Exception {
    SpitterRepository mockRepository = mock(SpitterRepository.class);
    SpitterController controller = new SpitterController(mockRepository);
    MockMvc mockMvc = standaloneSetup(controller).build();

    mockMvc.perform(get("/spitter/register"))//对“/spitter/register”发 送GET请求
            .andExpect(view().name("registerForm"));//断言结果的视图名为registerForm。
  }

  /**
   * 测试表单提交请求
   *
   * @throws Exception
   */
  @Test
  public void shouldProcessRegistration() throws Exception {
    SpitterRepository mockRepository = mock(SpitterRepository.class);
    Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
    Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
    when(mockRepository.save(unsaved)).thenReturn(saved);

    SpitterController controller = new SpitterController(mockRepository);
    MockMvc mockMvc = standaloneSetup(controller).build();

    mockMvc.perform(post("/spitter/register")
            .param("firstName", "Jack")
            .param("lastName", "Bauer")
            .param("username", "jbauer")
            .param("password", "24hours")
            .param("email", "jbauer@ctu.gov"))
            .andExpect(redirectedUrl("/spitter/jbauer"));//在处理POST类型的请求时，在请求处理完成后，最好进行一下重定 向，这样浏览器的刷新就不会重复提交表单了。在在这个测试中，预期 请求会重定向到“/spitter/jbauer”，也就是新建用户的基本信息页面

    verify(mockRepository, atLeastOnce()).save(unsaved);
  }
}
