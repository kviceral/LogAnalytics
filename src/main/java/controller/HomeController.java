package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by KrisViceral on 15/06/2015.
 */

@Controller
public class HomeController {

    @ResponseBody
    @RequestMapping("/")
    public String home(Model model){
        return "home";
    }
}
