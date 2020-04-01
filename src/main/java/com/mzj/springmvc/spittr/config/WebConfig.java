package com.mzj.springmvc.spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc//启用Spring MVC
@ComponentScan("com.mzj.springmvc.spittr.web")//指定组件扫描范围，可以扫描到带有@Controller注解的bean（spring mvc中的Controller）
public class WebConfig extends WebMvcConfigurerAdapter {

  /**
   * 添加视图解析器bean
   *
   * 本实现：
   *
   * 会查找.JSP文件，在查找的时候，它会在视图名称上加一个特定的前缀和后缀（例如，名为home的视图将会解析为/WEB-INF/views/home.jsp）。
   *
   * @return
   */
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
  }

  /**
   * 通过继承WebMvcConfigurerAdapter
   * 复写configureDefaultServletHandling
   * 调用 configurer.enable();
   * 达到的效果是：要求DispatcherServlet将对静态资源的请求转发到Servlet容 器中默认的Servlet上，而不是使用DispatcherServlet本身来处理 此类请求。
   * @param configurer
   */
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }
  
}
