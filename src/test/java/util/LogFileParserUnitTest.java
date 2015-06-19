package util;

import domain.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

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
}
