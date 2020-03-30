package com.mzj.springmvc.spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 编写基本的控制器
 *
 * @Auther: mazhongjia
 * @Date: 2020/3/30 12:44
 * @Version: 1.0
 */
@Controller//1、声明此类是一个控制器
public class HomeController {

    @RequestMapping(value="/",method = GET)//2、处理对“/”的GET请求
    public String home(){
        return"home";//3、视图名为”home“
    }
}
