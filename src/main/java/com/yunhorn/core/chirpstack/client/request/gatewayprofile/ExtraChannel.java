package com.yunhorn.core.chirpstack.client.request.gatewayprofile;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ljm
 * @date 2021/2/25 10:59
 */
@Data
public class ExtraChannel {
//    "bandwidth":0,
//            "bitrate":0,
//            "frequency":0,
//            "modulation":"LORA",
//            "spreadingFactors":[
//            0
//            ]
    private int bandwidth = 0;
    private int bitrate = 0;
    private int frequency = 0;
    private String modulation = "LORA";
    private List<Integer> spreadingFactors = Lists.newArrayList(0);

    @Override
    public boolean equals(Object o) {
        if (o==null || getClass() != o.getClass()) {
            return false;
        }
        ExtraChannel thisExtraChannel = this;
        ExtraChannel thatExtraChannel = (ExtraChannel) o;
        return thisExtraChannel.getBandwidth()==thatExtraChannel.getBandwidth()
                && thisExtraChannel.getBitrate()==thatExtraChannel.getBitrate()
                && thisExtraChannel.getFrequency()==thatExtraChannel.getFrequency()
                && Optional.ofNullable(thisExtraChannel.getModulation()).orElse("").equals(Optional.ofNullable(thatExtraChannel.getModulation()).orElse(""))
                && (thisExtraChannel.getSpreadingFactors().size()==thatExtraChannel.getSpreadingFactors().size() && thisExtraChannel.getSpreadingFactors().containsAll(thatExtraChannel.getSpreadingFactors()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(bandwidth, bitrate, frequency, modulation, spreadingFactors);
    }
}
