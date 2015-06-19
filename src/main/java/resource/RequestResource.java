package resource;

import domain.Request;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

/**
 * Created by KrisViceral on 18/06/2015.
 */

@RestController
public class RequestResource {

    @RequestMapping("/api/request")
    public Request request() {
        return createTestResource();
    }

    @RequestMapping("/api/requests")
    public Request getRequestWithSampleLog() {
        return null;
    }

    //Take in a file and regenate UI
    @RequestMapping(value = "/api/requests", method = RequestMethod.POST)
    public Request getRequestsWithGivenFile() {
        throw new UnsupportedOperationException();
    }

    private Request createTestResource(){

        Request request = new Request();

        ArrayList<String> visitors = new ArrayList<>();
        visitors.add("192.168.0.0");
        visitors.add("192.168.2.1");
        visitors.add("192.170.3.5");
        visitors.add("192.168.0.0");
        visitors.add("192.168.0.0");
        visitors.add("192.168.0.6");
        request.setVisitors(visitors);

        LocalDateTime jan1st = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 0);
        request.setDate(jan1st);

        return request;
    }
}
