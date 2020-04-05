package com.mzj.springmvc.spittr;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mzj.springmvc.spittr.data.SpittleRepository;
import com.mzj.springmvc.spittr.web.SpittleController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

public class SpittleControllerTest {

  @Test
  public void shouldShowRecentSpittles() throws Exception {
    //1、这个测试首先会创建SpittleRepository接口的mock实现，这个 实现会从它的findSpittles()方法中返回20个Spittle对象。
    List<Spittle> expectedSpittles = createSpittleList(20);
    SpittleRepository mockRepository = mock(SpittleRepository.class);//mock   SpittleRepository接口的实现
    when(mockRepository.findSpittles(Long.MAX_VALUE, 20))
        .thenReturn(expectedSpittles);

    //2、它将这个Repository注入到一个新的SpittleController 实例中，然后创建MockMvc并使用这个控制器
    SpittleController controller = new SpittleController(mockRepository);
    MockMvc mockMvc = standaloneSetup(controller)//mock   spring mvc
        .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))//这里设置的路径其实没有太大意义，只是与在WebConfig中定义的viewResolver保持一致
            //调用了setSingleView()。这样的话，mock框架就不用解析 控制器中的视图名了，之所以使用这种直接set视图名的方式，是因为视图名与请求路径是非常相似的（spittles），这样按照默认 的视图解析规则时，MockMvc就会发生失败，因为无法区分视图路 径和控制器的路径。
            .build();
    //3、开始断言：测试对“/spittles”发起GET请求，然后断言视图的名称为spittles并 且模型中包含名为spittleList的属性，在spittleList中包含 预期的内容。
    mockMvc.perform(get("/spittles"))//对/spittles发起GET请求
       .andExpect(view().name("spittles"))//断言期望值
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
