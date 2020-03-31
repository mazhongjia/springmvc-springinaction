package com.mzj.springmvc.spittr;

import com.mzj.springmvc.spittr.web.HomeController2;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HomeControllerTest2 {

  @Test
  public void testHomePage() throws Exception {
    HomeController2 controller = new HomeController2();

    MockMvc mockMvc = standaloneSetup(controller).build();
    mockMvc.perform(get("/"))
           .andExpect(view().name("home"));

    mockMvc.perform(get("/homePage"))
            .andExpect(view().name("home"));
  }

}
