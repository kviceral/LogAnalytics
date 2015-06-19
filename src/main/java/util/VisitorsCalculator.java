package util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KrisViceral on 18/06/2015.
 */
public class VisitorsCalculator {

    public static int computeUniqueVisitors(ArrayList<String> visitors){

        HashMap<String, Integer> ipAddresses = new HashMap();

        for (String ipAddress : visitors) {
            if(!isUnique(ipAddress, ipAddresses)) {
                ipAddresses.put(ipAddress, (0));
            }
        }

        return ipAddresses.size();
    }

    /*
        Gets the unique instances from visitors
        Puts a counter to each ip address (how many times that IP address visited)
    */
    public static HashMap<String, Integer> noOfVisitsPerIPAddress(ArrayList<String> visitors){
        HashMap<String, Integer> noOfVisitsPerIPAddress = new HashMap<>();

        for (String ipAddress : visitors) {
            if(isUnique(ipAddress, noOfVisitsPerIPAddress)){
                Integer counter = noOfVisitsPerIPAddress.get(ipAddress);
                counter++;

                noOfVisitsPerIPAddress.remove(ipAddress);
                noOfVisitsPerIPAddress.put(ipAddress, counter);
            }else{
                /*
                    We start the value at 1 instead of 0 because we are using value as
                    size instead of an index.
                 */
                noOfVisitsPerIPAddress.put(ipAddress, new Integer(1));
            }
        }

        return noOfVisitsPerIPAddress;
    }


    private static boolean isUnique(String ipAddress, HashMap<String,Integer> ipAddresses) {
        return ipAddresses.containsKey(ipAddress);
    }
}
