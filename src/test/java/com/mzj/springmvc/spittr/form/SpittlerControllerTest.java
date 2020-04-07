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
   * 测试展现表单
   *
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
    //1.构建SpitterRepository的mock实现，unsaved是调用save方法传递的参数，saved是方法返回值
    SpitterRepository mockRepository = mock(SpitterRepository.class);
    Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
    Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
    when(mockRepository.save(unsaved)).thenReturn(saved);

    //2.构建所要执行的控制器
    SpitterController controller = new SpitterController(mockRepository);
    //3.构建MockMvc
    MockMvc mockMvc = standaloneSetup(controller).build();

    mockMvc.perform(post("/spitter/register")//)对“/spitter/ register”发起了一个POST请求。
            .param("firstName", "Jack")
            .param("lastName", "Bauer")
            .param("username", "jbauer")
            .param("password", "24hours")
            .param("email", "jbauer@ctu.gov"))
            .andExpect(redirectedUrl("/spitter/jbauer"));//在处理POST类型的请求时，在请求处理完成后，最好进行一下重定 向，这样浏览器的刷新就不会重复提交表单了。在在这个测试中，预期 请求会重定向到“/spitter/jbauer”，也就是新建用户的基本信息页面

    verify(mockRepository, atLeastOnce()).save(unsaved);//校验SpitterRepository的mock实现最终会真正用 来保存表单上传入的数据
  }
}
