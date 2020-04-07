package com.mzj.springmvc.spittr.web.form;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.mzj.springmvc.spittr.Spitter;
import com.mzj.springmvc.spittr.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

  private SpitterRepository spitterRepository;

  @Autowired
  public SpitterController(SpitterRepository spitterRepository) {
    this.spitterRepository = spitterRepository;
  }

  /**
   * 展现表单控制器
   * @return
   */
  @RequestMapping(value="/register", method=GET)
  public String showRegistrationForm() {
    return "registerForm";
  }

  /**
   * 处理表单提交控制器
   * @param spitter
   * @return
   */
  @RequestMapping(value="/register", method=POST)
  public String processRegistration(Spitter spitter) {
    /**
     * 这个控制器方法直接声明接收一个Spittler对象参数，需要依赖发送请求的客户端：在请求中使用与Spittler对象中属性同名的参数，这样Spittler对象的属性会自动进行填充，对于本测试来说，是SpittlerControllerTest的shouldProcessRegistration中的下面代码起到的作用：
     *
     * mockMvc.perform(post("/spitter/register")//)对“/spitter/ register”发起了一个POST请求。
     *             .param("firstName", "Jack")
     *             .param("lastName", "Bauer")
     *             .param("username", "jbauer")
     *             .param("password", "24hours")
     *             .param("email", "jbauer@ctu.gov"))
     */
    System.out.println(spitter);
    spitterRepository.save(spitter);//当使用Spitter对象调用processRegistration()方法时，它会进而调用SpitterRepository的save()方法，SpitterRepository是在Spitter-Controller的构造器中注入进来的。
    //返回的值还带有重定向的格式
    //请求重定向：当InternalResourceViewResolver看到视图格式中 的“redirect:”前缀时，它就知道要将其解析为重定向的规则，而不是视图的名称。
    //请求转发：除了“redirect:”，InternalResourceViewResolver还能识 别“forward:”前缀。当它发现视图格式中以“forward:”作为前缀时，请求将会前往（forward）指定的URL路径，而不再是重定向。
    return "redirect:/spitter/" + spitter.getUsername();//在处理POST类型的请求时，在请求处理完成后，最好进行一下重定向，这样浏览器的刷新就不会重复提交表单了。在在这个测试中，预期 请求会重定向到“/spitter/jbauer”，也就是新建用户的基本信息页面
  }

  /**
   * 处理提交表单后处理完重定向请求的跳转，也就是基本信息页面的请求
   * @param username
   * @param model
   * @return
   */
  @RequestMapping(value="/{username}", method=GET)
  public String showSpitterProfile(@PathVariable String username, Model model) {
    /**
     * SpitterRepository通过用户名获取一个Spitter对 象，showSpitter-Profile()得到这个对象并将其添加到模型 中，然后返回profile，也就是基本信息页面的逻辑视图名。
     */
    Spitter spitter = spitterRepository.findByUsername(username);
    model.addAttribute(spitter);
    return "profile";
  }
}
