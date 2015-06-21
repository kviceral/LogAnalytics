package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;

/**
 * Created by KrisViceral on 21/06/2015.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class ApacheAccess {

    private String clientip;
    private String timestamp;

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
