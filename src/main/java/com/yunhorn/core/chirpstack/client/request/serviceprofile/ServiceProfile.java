package com.yunhorn.core.chirpstack.client.request.serviceprofile;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * @author ljm
 * @date 2021/2/25 15:07
 */
@Data
@Slf4j
public class ServiceProfile {
//    "addGWMetaData":true,
//            "channelMask":"string",
//            "devStatusReqFreq":0,
//            "dlBucketSize":0,
//            "dlRate":0,
//            "dlRatePolicy":"DROP",
//            "drMax":0,
//            "drMin":0,
//            "hrAllowed":true,
//            "id":"string",
//            "minGWDiversity":0,
//            "name":"string",
//            "networkServerID":"string",
//            "nwkGeoLoc":true,
//            "organizationID":"string",
//            "prAllowed":true,
//            "raAllowed":true,
//            "reportDevStatusBattery":true,
//            "reportDevStatusMargin":true,
//            "targetPER":0,
//            "ulBucketSize":0,
//            "ulRate":0,
//            "ulRatePolicy":"DROP"
    private boolean addGWMetaData = true;
    private String channelMask;
    private int devStatusReqFreq = 0;
    private int dlBucketSize = 0;
    private int dlRate = 0;
    private String dlRatePolicy = "DROP";
    private int drMax = 0;
    private int drMin = 0;
//    private boolean gwsPrivate = true;
    private boolean hrAllowed = true;
    private String id;
    private int minGWDiversity = 0;
    private String name;
    private String networkServerID;
    private boolean nwkGeoLoc = true;
    private String organizationID;
    private boolean prAllowed = true;
    private boolean raAllowed = true;
    private boolean reportDevStatusBattery = true;
    private boolean reportDevStatusMargin = true;
    private int targetPER = 0;
    private int ulBucketSize = 0;
    private int ulRate = 0;
    private String ulRatePolicy = "DROP";

    @Override
    public boolean equals(Object o) {
        if (o==null || getClass() != o.getClass()) {
            return false;
        }
        ServiceProfile thisServiceProfile = this;
        ServiceProfile thatServiceProfile = (ServiceProfile) o;
        //id networkServerID organizationID不比较
        return thisServiceProfile.isAddGWMetaData()==thatServiceProfile.isAddGWMetaData()
                && Optional.ofNullable(thisServiceProfile.getChannelMask()).orElse("").equals(Optional.ofNullable(thatServiceProfile.getChannelMask()).orElse(""))
                && thisServiceProfile.getDevStatusReqFreq()==thatServiceProfile.getDevStatusReqFreq()
                && thisServiceProfile.getDlBucketSize()==thatServiceProfile.getDlBucketSize()
                && thisServiceProfile.getDlRate()==thatServiceProfile.getDlRate()
                && Optional.ofNullable(thisServiceProfile.getDlRatePolicy()).orElse("").equals(Optional.ofNullable(thatServiceProfile.getDlRatePolicy()).orElse(""))
                && thisServiceProfile.getDrMax()==thatServiceProfile.getDrMax()
                && thisServiceProfile.getDrMin()==thatServiceProfile.getDrMin()
                && thisServiceProfile.isHrAllowed()==thatServiceProfile.isHrAllowed()
                && thisServiceProfile.getMinGWDiversity()==thatServiceProfile.getMinGWDiversity()
                && Optional.ofNullable(thisServiceProfile.getName()).orElse("").equals(Optional.ofNullable(thatServiceProfile.getName()).orElse(""))
                && thisServiceProfile.isNwkGeoLoc()==thatServiceProfile.isNwkGeoLoc()
                && thisServiceProfile.isPrAllowed()==thatServiceProfile.isPrAllowed()
                && thisServiceProfile.isRaAllowed()==thatServiceProfile.isRaAllowed()
                && thisServiceProfile.isReportDevStatusBattery()==thatServiceProfile.isReportDevStatusBattery()
                && thisServiceProfile.isReportDevStatusMargin()==thatServiceProfile.isReportDevStatusMargin()
                && thisServiceProfile.getTargetPER()==thatServiceProfile.getTargetPER()
                && thisServiceProfile.getUlBucketSize()==thatServiceProfile.getUlBucketSize()
                && thisServiceProfile.getUlRate()==thatServiceProfile.getUlRate()
                && Optional.ofNullable(thisServiceProfile.getUlRatePolicy()).orElse("").equals(Optional.ofNullable(thatServiceProfile.getUlRatePolicy()).orElse(""));
    }

    @Override
    public int hashCode() {
        return Objects.hash(addGWMetaData, channelMask, devStatusReqFreq, dlBucketSize
                , dlRate, dlRatePolicy, drMax, drMin, hrAllowed, minGWDiversity, name
                , nwkGeoLoc, prAllowed, raAllowed
                , reportDevStatusBattery, reportDevStatusMargin, targetPER
                , ulBucketSize, ulRate, ulRatePolicy);
    }

    public ServiceProfile copyProperties(String targetOrganizationID,String targetNetworkServerID,String targetId){
        ServiceProfile targetServiceProfile = new ServiceProfile();
        try {
            BeanUtils.copyProperties(targetServiceProfile,this);
        }catch (Exception e){
            log.error("CopyProperties sourceServiceProfile to targetServiceProfile error",e);
        }
        targetServiceProfile.setId(targetId);
        targetServiceProfile.setOrganizationID(targetOrganizationID);
        targetServiceProfile.setNetworkServerID(targetNetworkServerID);
        return targetServiceProfile;
    }
}
