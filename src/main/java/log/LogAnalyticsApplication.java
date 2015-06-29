package log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"controller","resource"})
public class LogAnalyticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogAnalyticsApplication.class, args);
    }
}
