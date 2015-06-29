package resource;

import domain.Request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.LogFileParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

/**
 * Created by KrisViceral on 18/06/2015.
 */

@RestController
@RequestMapping("/api/requests/")
public class RequestResource {
    private static final String ACCESS_LOG_FILE_PATH = "/sample/access.log";

    @RequestMapping(value = "sample", method = RequestMethod.GET)
    public ArrayList<Request> getRequestWithSampleLog() throws IOException{
        LogFileParser logFileParser = new LogFileParser();
        return logFileParser.getRequests();
    }

    @RequestMapping(value = "access", method = RequestMethod.GET)
    public ArrayList<Request> getRequestWithAccessLog() throws IOException{
        return new LogFileParser(ACCESS_LOG_FILE_PATH).getRequests();
    }

    //Take in a file and regenate UI
    @RequestMapping(method = RequestMethod.POST)
    public Request getRequestsWithGivenFile() {
        throw new UnsupportedOperationException("Feature not supported yet");
    }

}
