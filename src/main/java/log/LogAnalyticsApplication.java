package log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"controller","resource"})
public class LogAnalyticsApplication {

    public static void main(String[] args) {
        /*
            TODO:
            - LogfileParser - continue
            - UI get data
            - UI display a chart
         */
        SpringApplication.run(LogAnalyticsApplication.class, args);
    }
}
