package controller;

import domain.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

/**
 * Created by KrisViceral on 14/06/2015.
 */

@Controller
public class RequestController {

    @RequestMapping("/request")
    public String request(Model model){

        //test
        Request request = new Request();

        //visitors
        ArrayList<String> visitors = new ArrayList<String>();
        visitors.add("192.168.0.0");
        visitors.add("192.168.2.1");
        visitors.add("192.170.3.5");
        request.setVisitors(visitors);

        //datetime
        LocalDateTime jan1st = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 0);
        request.setDate(jan1st);

        return null;
    }
}
