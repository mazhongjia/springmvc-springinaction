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
@Controller
@RequestMapping({"/","/homePage"})
public class HomeController2 {

    @RequestMapping(method = GET)
    public String home(){
        return"home";
    }
}
