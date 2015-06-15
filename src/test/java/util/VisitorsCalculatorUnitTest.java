package util;

import domain.Request;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by KrisViceral on 14/06/2015.
 */
public class VisitorsCalculatorUnitTest {

    Request request;

    @Before
    public void prepare(){
        request = new Request();

        ArrayList<String> visitors = new ArrayList<>();
        visitors.add("192.168.0.0");
        visitors.add("192.168.2.1");
        visitors.add("192.170.3.5");
        request.setVisitors(visitors);

        LocalDateTime jan1st = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 0);
        request.setDate(jan1st);
    }

    @Test public void
    computeTotalUniqueVisitors_should_return_3(){
        assertEquals(3, VisitorsCalculator.computeUniqueVisitors(request.getVisitors()));
    }

    @Test public void
    noOfVisitsPerIPAddress_should_return_3_given_192_168_0_0(){

        String visitorToCheck = "192.168.0.0";

        ArrayList<String> visitors = new ArrayList<>();
        visitors.add("192.168.0.0");
        visitors.add("192.168.2.1");
        visitors.add("192.168.0.0");
        visitors.add("192.170.3.5");
        visitors.add("192.168.0.0");
        request.setVisitors(visitors);

        HashMap<String, Integer> noOfVisitsPerIPAddress = VisitorsCalculator.noOfVisitsPerIPAddress(request.getVisitors());
        Integer noOfvisits = noOfVisitsPerIPAddress.get(visitorToCheck);

        assertEquals(3, noOfvisits.intValue());
    }
}
