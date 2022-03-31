package com.yunhorn.core.chirpstack.client.response.gateway;

import lombok.Data;

import java.util.Objects;
import java.util.Optional;

/**
 * @author ljm
 * @date 2021/2/25 11:46
 */
@Data
public class Location {
//    "accuracy":0,
//            "altitude":0,
//            "latitude":0,
//            "longitude":0,
//            "source":"UNKNOWN"
    private int accuracy = 0;
    private int altitude = 0;
    private int latitude = 0;
    private int longitude = 0;
    private String source = "UNKNOWN";

    @Override
    public boolean equals(Object o) {
        if (o==null || getClass() != o.getClass()) {
            return false;
        }
        Location thisLocation = this;
        Location thatLocation = (Location) o;
        return thisLocation.getAccuracy()==thatLocation.getAccuracy() && thisLocation.getAltitude()==thatLocation.getAltitude()
                && thisLocation.getLatitude()==thatLocation.getLatitude() && thisLocation.getLongitude()==thatLocation.getLongitude()
                && Optional.ofNullable(thisLocation.getSource()).orElse("").equals(Optional.ofNullable(thatLocation.getSource()).orElse(""));
    }

    @Override
    public int hashCode() {
        return Objects.hash(accuracy, altitude, latitude, longitude, source);
    }
}
