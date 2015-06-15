package log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class LogAnalyticsApplication {

    @ResponseBody
    @RequestMapping("/")
    public String helloWorld(){
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(LogAnalyticsApplication.class, args);
    }
}
