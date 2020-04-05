package com.mzj.springmvc.spittr.web.param;

import com.mzj.springmvc.spittr.Spittle;
import com.mzj.springmvc.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @Auther: mazhongjia
 * @Date: 2020/3/31 13:06
 * @Version: 1.0
 */
@Controller
@RequestMapping("/spittles2")
public class SpittleController2 {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";
    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController2(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

//    @RequestMapping(method = GET)
//    public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
//                                  @RequestParam(value = "count", defaultValue = "20") int count) {
//        return spittleRepository.findSpittles(max, count);
//    }

    @RequestMapping(method = GET)
    public String spittles(Model model,@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
                                  @RequestParam(value = "count", defaultValue = "20") int count) {
        System.out.println("controller收到：count = " + count + "，max = " + max);
        model.addAttribute("spittleList", spittleRepository.findSpittles(max,count));
        return "spittles";
    }


}
