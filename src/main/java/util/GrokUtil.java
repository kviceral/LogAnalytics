package util;

import oi.thekraken.grok.api.Grok;
import oi.thekraken.grok.api.Match;
import oi.thekraken.grok.api.exception.GrokException;

import java.io.*;

/**
 * Created by KrisViceral on 21/06/2015.
 */
public class GrokUtil {

    private final String GROK_PATTERNS_FILE_FILE_PATH = "/patterns/patterns";
    private final String GROK_PATTERN = "%{COMMONAPACHELOG}";
    private Grok grok;

    public GrokUtil(){
        init();
    }

    private void init(){
        try {
            InputStream is = getClass().getResourceAsStream(GROK_PATTERNS_FILE_FILE_PATH);
            Reader reader = new InputStreamReader(is);

            grok = new Grok();
            grok.addPatternFromReader(reader);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public String parseApacheLogLine(String logLine) throws GrokException{
        grok.compile(GROK_PATTERN);
        Match gm = grok.match(logLine);
        gm.captures();
        return gm.toJson();
    }
}
