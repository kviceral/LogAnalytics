package util;

import domain.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by KrisViceral on 18/06/2015.
 */
public class LogFileParser {

    private final String SAMPLE_FILE_FILE_PATH = "/sample/sample.log";
    private ArrayList<String> logFile;
    private ArrayList<Request> requests;

    //Instantiate with sample data
    public LogFileParser() throws IOException{

        logFile = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream(SAMPLE_FILE_FILE_PATH);
        init(inputStream);
    }

    //Instantiate by sending a file
    public LogFileParser(String placeholder){
        throw new UnsupportedOperationException();
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public ArrayList<String> getLogFile() {
        return logFile;
    }

    private void init(InputStream inputStream) throws IOException{
        if (inputStream == null) {
            throw new IOException("File Not Found");
        } else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    logFile.add(line);
                }
                requests = parseLogFile(logFile);
            } catch (IOException ex) {
                throw new IOException(ex);
            }
        }
    }

    private ArrayList<Request> parseLogFile(ArrayList<String> logFile){

        /*
            Todo
            -import grok
            -use grok
            -fill up request
         */
        return null;
    }
}
