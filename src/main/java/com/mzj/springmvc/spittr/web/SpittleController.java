package com.mzj.springmvc.spittr.web;

import com.mzj.springmvc.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.Map;

/**
 * @Auther: mazhongjia
 * @Date: 2020/3/31 13:06
 * @Version: 1.0
 */
@Controller(value = "spittleController")
@RequestMapping("/spittles")
public class SpittleController {

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    /**
     * 在spittles()方法中给定了一个Model作为参 数。这样，spittles()方法就能将Repository中获取到的 Spittle列表填充到模型中。Model实际上就是一个Map（也就是 key-value对的集合），它会传递给视图，这样数据就能渲染到客户端了。
     * <p>
     * 以下两个注释方法，与本方法达到的效果一致
     *
     * @param model
     * @return
     */
    @RequestMapping(method = GET)
    public String spittles(Model model) {//如果你希望使用非Spring类型的话，那么可以用java.util.Map来 代替Model。实际上Model就是Map
        //当调用addAttribute()方法并且不指定key的时候，那么key会 根据值的对象类型推断确定。在本例中，因为它是一 个List<Spittle>，因此，键将会推断为spittleList。
        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));//将spittle添加到模型中
        return "spittles";//返回视图名,spittles()方法所做的最后一件事是返回spittles作为视图的名字，这个视图会渲染模型。
    }

//    public String spittles(Map model){/
//        model.put("spittleList",spittleRepository.findSpittles(Long.MAX_VALUE,20));
//        return "spittles";
//    }

//    public List<Spittle> spittles() {//这个版本与其他的版本有些差别。它并没有返回视图名称，也没有显 式地设定模型，这个方法返回的是Spittle列表。当处理器方法像 这样返回对象或集合时，这个值会放到模型中，模型的key会根据其 类型推断得出（在本例中，也就是spittleList）,而逻辑视图的名称将会根据请求路径推断得出。因为这个方法处理针 对“/spittles”的GET请求，因此视图的名称将会是spittles（去掉开 头的斜线）。
//        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
//    }


}
