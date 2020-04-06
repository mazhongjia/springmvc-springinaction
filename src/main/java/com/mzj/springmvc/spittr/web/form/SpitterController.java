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
   * 处理跳转到表单提交页到请求
   * @return
   */
  @RequestMapping(value="/register", method=GET)
  public String showRegistrationForm() {
    return "registerForm";
  }

  /**
   * 处理表单提交请求
   * @param spitter
   * @param errors
   * @return
   */
  @RequestMapping(value="/register", method=POST)
  public String processRegistration(
          @Valid Spitter spitter,
          Errors errors) {
    if (errors.hasErrors()) {
      return "registerForm";
    }

    spitterRepository.save(spitter);
    //返回的值还带有重定向的格式
    //请求重定向：当InternalResourceViewResolver看到视图格式中 的“redirect:”前缀时，它就知道要将其解析为重定向的规则，而不是视图的名称。
    //请求转发：除了“redirect:”，InternalResourceViewResolver还能识 别“forward:”前缀。当它发现视图格式中以“forward:”作为前缀 时，请求将会前往（forward）指定的URL路径，而不再是重定向。
    return "redirect:/spitter/" + spitter.getUsername();////在处理POST类型的请求时，在请求处理完成后，最好进行一下重定 向，这样浏览器的刷新就不会重复提交表单了。在在这个测试中，预期 请求会重定向到“/spitter/jbauer”，也就是新建用户的基本信息页面
  }

  /**
   * 处理提交表单后处理完重定向请求的跳转
   * @param username
   * @param model
   * @return
   */
  @RequestMapping(value="/{username}", method=GET)
  public String showSpitterProfile(@PathVariable String username, Model model) {
    Spitter spitter = spitterRepository.findByUsername(username);
    model.addAttribute(spitter);
    return "profile";
  }
}
