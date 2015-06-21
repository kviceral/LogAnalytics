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

    @Before
    public void prepare(){
        try {
            logFileParser = new LogFileParser();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test public void
    getLogFile_should_return_arraylist_with_string_with_ip_172_21_13_45(){

        String line = "172.16.42.68 - AWAY [08/May/2015:13:32:04 -0800] \"GET /scripts/iisadmin/ism.dll?http/serv" +
                " HTTP/1.0\" 200 3401";

        ArrayList<String> logFile = logFileParser.getLogFile();
        assertThat(logFile.contains(line), is(true));
    }

    @Test public void
    getRequests_should_return_null(){
        assertThat(logFileParser.getRequests(), nullValue());
    }


    @Test public void
    mapJsonToApacheAccess_should_return_apacheaccess_given_json(){
        String json = "{\"BASE10NUM\":3401,\"COMMONAPACHELOG\":\"172.16.42.68 - AWAY [08/May/2015:12:40:04 -0800] \\\"GET /scripts/iisadmin/ism.dll?http/serv HTTP/1.0\\\" 200 3401\",\"HOSTNAME\":\"172.16.42.68\",\"HOUR\":12,\"INT\":-800,\"MINUTE\":40,\"MONTH\":\"May\",\"MONTHDAY\":8,\"SECOND\":4,\"TIME\":\"12:40:04\",\"USERNAME\":\"AWAY\",\"YEAR\":2015,\"auth\":\"AWAY\",\"bytes\":3401,\"clientip\":\"172.16.42.68\",\"httpversion\":\"1.0\",\"ident\":\"-\",\"request\":\"/scripts/iisadmin/ism.dll?http/serv\",\"response\":200,\"timestamp\":\"08/May/2015:12:40:04 -0800\",\"verb\":\"GET\"}\n";
        String ip = "172.16.42.68";

        ApacheAccess apacheAccess = logFileParser.mapJsonToApacheAccess(json);
        assertThat(apacheAccess.getClientip(), equalTo(ip));
    }

    @Test public void
    parseLogFile_should_return_requests_with_given_IP(){
        String userIP = "127.20.30.11";
        ArrayList<Request> requests = logFileParser.parseLogFile(logFileParser.getLogFile());
        boolean doesRequestContainUserIP = false;

        for (Request request: requests){
            ArrayList<String> visitors = request.getVisitors();
            doesRequestContainUserIP = visitors.contains(userIP);
        }

        assertThat(doesRequestContainUserIP, is(true));
    }

}
