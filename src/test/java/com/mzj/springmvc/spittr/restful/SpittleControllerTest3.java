package com.mzj.springmvc.spittr.restful;

import com.mzj.springmvc.spittr.Spittle;
import com.mzj.springmvc.spittr.data.SpittleRepository;
import com.mzj.springmvc.spittr.web.SpittleController;
import com.mzj.springmvc.spittr.web.param.SpittleController2;
import com.mzj.springmvc.spittr.web.restful.SpittleController3;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpittleControllerTest3 {

  @Test
  public void testSpittle() throws Exception {
    Spittle expectedSpittle = new Spittle("Hello", new Date());
    SpittleRepository mockRepository = mock(SpittleRepository.class);//构建了一个mock Repository
    when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);

    SpittleController3 controller = new SpittleController3(mockRepository);//构建了一个控制器
    MockMvc mockMvc = standaloneSetup(controller).build();

    mockMvc.perform(get("/spittles3/12345"))//构建了一个mock mvc，对“/spittles/12345”发起GET请求
            .andExpect(view().name("spittles"))
            .andExpect(model().attributeExists("spittle"))
            .andExpect(model().attribute("spittle", expectedSpittle));
  }
}
