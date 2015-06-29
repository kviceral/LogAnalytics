package util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.ApacheAccess;
import domain.Request;
import oi.thekraken.grok.api.exception.GrokException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by KrisViceral on 18/06/2015.
 *
 * Reconsider storing the logFile. Might be huge in memory.
 */
public class LogFileParser {

    private static final String TIMESTAMP_FORMAT = "dd/MMM/yyyy:HH:mm:ss Z";
    private static final String SAMPLE_FILE_FILE_PATH = "/sample/sample.log";
    private ArrayList<String> logFile;
    private ArrayList<Request> requests;

    //Instantiate with sample data
    public LogFileParser() throws IOException{
        init(SAMPLE_FILE_FILE_PATH);
    }

    //Instantiate by sending a file
    public LogFileParser(String filePath) throws IOException{
        init(filePath);
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public ArrayList<String> getLogFile() {
        return logFile;
    }

    private void init(String filePath) throws IOException{
        logFile = new ArrayList<>();
        requests = new ArrayList<>();

        InputStream inputStream = getClass().getResourceAsStream(filePath);
        GrokUtil grokUtil = new GrokUtil();

        if (inputStream == null) {
            throw new IOException("File Not Found");
        } else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {

                    String json = grokUtil.parseApacheLogLine(line);
                    ApacheAccess apacheAccess = mapJsonToApacheAccess(json);

                    LocalDateTime dateTime = roundDownOnHour(apacheAccess.getTimestamp());
                    Request existingRequest = findRequestWithDateTime(dateTime);

                    if(existingRequest != null){
                        existingRequest.getVisitors().add(apacheAccess.getClientip());
                    }else{
                        ArrayList<String> visitors = new ArrayList<>();
                        visitors.add(apacheAccess.getClientip());

                        Request request = new Request(visitors, dateTime);
                        requests.add(request);
                    }
                }


            } catch (IOException ex) {
                throw new IOException(ex);
            } catch (GrokException e) {
                e.printStackTrace();
            }
        }
    }


    private LocalDateTime roundDownOnHour(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
        LocalDateTime dateTime = LocalDateTime.parse(timestamp, formatter);

        dateTime = dateTime.minusMinutes(dateTime.getMinute());
        dateTime = dateTime.minusSeconds(dateTime.getSecond());
        return dateTime;
    }


    private Request findRequestWithDateTime(LocalDateTime dateTime){

        for(Request request: requests){
            LocalDateTime requestDateTime = request.getDate();
            if(dateTime.equals(requestDateTime))
                return request;
        }
        return null;
    }

    public ApacheAccess mapJsonToApacheAccess(String json){
        try {
            ObjectMapper mapper = new ObjectMapper();
            ApacheAccess apacheAccess = mapper.readValue(json, ApacheAccess.class);
            return apacheAccess;
        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
