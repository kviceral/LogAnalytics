package domain;

import util.VisitorsCalculator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KrisViceral on 14/06/2015.
 */
public class Request {

    private ArrayList<String> visitors; //IPAddress
    private LocalDateTime date;

    public ArrayList<String> getVisitors() {
        return visitors;
    }

    public void setVisitors(ArrayList<String> visitors) {
        this.visitors = visitors;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getTotalUniqueVisitors() {
        return VisitorsCalculator.computeUniqueVisitors(visitors);
    }

}
