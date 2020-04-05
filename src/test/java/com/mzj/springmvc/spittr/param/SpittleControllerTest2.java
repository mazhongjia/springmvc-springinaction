package com.mzj.springmvc.spittr.param;

import com.mzj.springmvc.spittr.Spittle;
import com.mzj.springmvc.spittr.data.SpittleRepository;
import com.mzj.springmvc.spittr.web.param.SpittleController2;
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

public class SpittleControllerTest2 {

  @Test
  public void shouldShowPagedSpittles() throws Exception {
    List<Spittle> expectedSpittles = createSpittleList(50);
    SpittleRepository mockRepository = mock(SpittleRepository.class);
    when(mockRepository.findSpittles(238900, 50))//预期的max和count参数
        .thenReturn(expectedSpittles);

    SpittleController2 controller = new SpittleController2(mockRepository);
    MockMvc mockMvc = standaloneSetup(controller)//mock   spring mvc
        .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
            .build();

    mockMvc.perform(get("/spittles2?max=238900&count=50"))//对/spittles发起GET请求，同时传入max和count参数，它测试 了这些参数存在时的处理器方法
       .andExpect(view().name("spittles"))
       .andExpect(model().attributeExists("spittleList"))
       .andExpect(model().attribute("spittleList",
                  hasItems(expectedSpittles.toArray())));
  }

  private List<Spittle> createSpittleList(int count) {
    List<Spittle> spittles = new ArrayList<Spittle>();
    for (int i=0; i < count; i++) {
      spittles.add(new Spittle("Spittle " + i, new Date()));
    }
    return spittles;
  }
}
