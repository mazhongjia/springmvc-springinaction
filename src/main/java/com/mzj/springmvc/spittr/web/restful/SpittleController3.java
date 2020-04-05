package com.mzj.springmvc.spittr.web.restful;

import com.mzj.springmvc.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @Auther: mazhongjia
 * @Date: 2020/3/31 13:06
 * @Version: 1.0
 */
@Controller
@RequestMapping("/spittles3")
public class SpittleController3 {

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController3(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(value = "/{spittleId}",method = GET)//其中spittleId是URL路径中的占位符，占位符的名称要用大括号（“{”和“}”）括起来。 路径中的其他部分要与所处理的请求完全匹配，但是占位符部分可以 是任意的值。
//    public String spittles(Model model, @PathVariable("spittleId") long spittleId) {//这里的spittleId与RequestMapping中/{spittleId}对应
//    需要注意的是：代码中spittleId这个词出现了好几次：先是 在@RequestMapping的路径中，然后作为@PathVariable属性的 值，最后又作为方法的参数名称。因为方法的参数名碰巧与占位符的 名称相同，因此我们可以去掉@PathVariable中的value属性：
    public String spittles(Model model, @PathVariable long spittleId) {//这里参数名spittleId与RequestMapping中请求占位符/{spittleId}对应
        System.out.println("controller收到：spittleId = " + spittleId);
        model.addAttribute(spittleRepository.findOne(spittleId));//将查询到的模型放入model中，以便在视图中拿到，模型的key将会是spittle，这是根据传递 到addAttribute()方法中的类型推断得到的
        return "spittles";
    }
}
