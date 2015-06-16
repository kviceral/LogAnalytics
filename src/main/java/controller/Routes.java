package controller;

/**
 * Created by KrisViceral on 15/06/2015.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Maps all AngularJS routes to index so that they work with direct linking.
 * This is currently a test. Spring interferes with Angular's router.
 */
@Controller
public class Routes {

    @RequestMapping({
            "/test",
            "/home"
    })
    public String index() {
        return "forward:/index.html";
    }

}