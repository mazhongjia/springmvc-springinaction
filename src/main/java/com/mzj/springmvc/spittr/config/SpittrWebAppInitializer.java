package com.mzj.springmvc.spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spittr的Web应用初始化器
 *
 * Spittr是应用名
 *
 * 扩 展AbstractAnnotation- ConfigDispatcherServletInitializer的任意类都会自动地 配置Dispatcher-Servlet和Spring应用上下文，Spring的应用上下 文会位于应用程序的Servlet上下文之中。
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};//将DispatcherServlet映射到“/”
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};//getRootConfigClasses()方法返回的带有@Configuration注解的类将会用来配置ContextLoaderListener负责创建的应用上下文中的bean。
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};//getServletConfigClasses()方法返回的带有@Configuration注解的类将会用来定义DispatcherServlet负责创建的Spring应用上下文中的bean
    }


}
