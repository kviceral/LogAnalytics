package util;

import domain.ApacheAccess;
import domain.Request;
import oi.thekraken.grok.api.exception.GrokException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by KrisViceral on 18/06/2015.
 */
public class LogFileParserUnitTest {

    private LogFileParser logFileParser;
    private static final String ACCESS_LOG_FILE_PATH = "/sample/access.log";

    /*
        Test Preparations
     */
    private void prepareWithSampleFile(){
        try {
            logFileParser = new LogFileParser();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private void prepareWithGivenFile(){
        try {
            logFileParser = new LogFileParser(ACCESS_LOG_FILE_PATH);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test public void
    mapJsonToApacheAccess_should_return_apacheaccess_given_json(){
        String json = "{\"BASE10NUM\":3401,\"COMMONAPACHELOG\":\"172.16.42.68 - AWAY [08/May/2015:12:40:04 -0800] \\\"" +
                "GET /scripts/iisadmin/ism.dll?http/serv HTTP/1.0\\\" 200 3401\",\"HOSTNAME\":\"172.16.42.68\",\"HOUR\":" +
                "12,\"INT\":-800,\"MINUTE\":40,\"MONTH\":\"May\",\"MONTHDAY\":8,\"SECOND\":4,\"TIME\":\"12:40:04\",\"" +
                "USERNAME\":\"AWAY\",\"YEAR\":2015,\"auth\":\"AWAY\",\"bytes\":3401,\"clientip\":\"172.16.42.68\",\"" +
                "httpversion\":\"1.0\",\"ident\":\"-\",\"request\":\"/scripts/iisadmin/ism.dll?http/serv\",\"response" +
                "\":200,\"timestamp\":\"08/May/2015:12:40:04 -0800\",\"verb\":\"GET\"}\n";
        String ip = "172.16.42.68";

        prepareWithSampleFile();
        ApacheAccess apacheAccess = logFileParser.mapJsonToApacheAccess(json);
        assertThat(apacheAccess.getClientip(), equalTo(ip));
    }

    @Test public void
    parseLogFile_should_return_requests_with_given_IP(){
        String userIP = "127.20.30.11";

        prepareWithSampleFile();
        ArrayList<Request> requests = logFileParser.getRequests();
        boolean doesRequestContainUserIP = doesIPExistInRequest(userIP, requests);
        assertThat(doesRequestContainUserIP, is(true));
    }

    @Test public void
    parseLogFile_given_file_should_return_requests_with_given_IP(){
        String userIP = "180.153.163.191";

        prepareWithGivenFile();
        ArrayList<Request> requests = logFileParser.getRequests();
        boolean doesRequestContainUserIP = doesIPExistInRequest(userIP, requests);
        assertThat(doesRequestContainUserIP, is(true));
    }



    private boolean doesIPExistInRequest(String IP, ArrayList<Request> requests){
        for (Request request: requests){
            ArrayList<String> visitors = request.getVisitors();
            if (visitors.contains(IP)){
                return true;
            }
        }
        return false;
    }

}
