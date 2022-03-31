package com.yunhorn.core.chirpstack.sync;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.yunhorn.core.chirpstack.client.api.*;
import com.yunhorn.core.chirpstack.client.request.application.Application;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsGetReq;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsPostReq;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsPutReq;
import com.yunhorn.core.chirpstack.client.request.device.*;
import com.yunhorn.core.chirpstack.client.request.deviceprofile.DeviceProfileGetReq;
import com.yunhorn.core.chirpstack.client.request.gateway.Gateway;
import com.yunhorn.core.chirpstack.client.request.gateway.GatewayGetReq;
import com.yunhorn.core.chirpstack.client.request.gateway.GatewayPostReq;
import com.yunhorn.core.chirpstack.client.request.gateway.GatewayPutReq;
import com.yunhorn.core.chirpstack.client.request.gatewayprofile.GatewayProfile;
import com.yunhorn.core.chirpstack.client.request.gatewayprofile.GatewayProfileGetReq;
import com.yunhorn.core.chirpstack.client.request.gatewayprofile.GatewayProfilePostReq;
import com.yunhorn.core.chirpstack.client.request.gatewayprofile.GatewayProfilePutReq;
import com.yunhorn.core.chirpstack.client.request.networkserver.NetworkServerGetReq;
import com.yunhorn.core.chirpstack.client.request.networkserver.NetworkServerPutReq;
import com.yunhorn.core.chirpstack.client.request.organization.OrganizationGetReq;
import com.yunhorn.core.chirpstack.client.request.serviceprofile.ServiceProfile;
import com.yunhorn.core.chirpstack.client.request.serviceprofile.ServiceProfileGetReq;
import com.yunhorn.core.chirpstack.client.request.serviceprofile.ServiceProfilePostReq;
import com.yunhorn.core.chirpstack.client.request.serviceprofile.ServiceProfilePutReq;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsGetInfoResp;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsGetResp;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsGetResult;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsPostResp;
import com.yunhorn.core.chirpstack.client.response.device.*;
import com.yunhorn.core.chirpstack.client.response.deviceprofile.DeviceProfileGetResp;
import com.yunhorn.core.chirpstack.client.response.deviceprofile.DeviceProfileGetResult;
import com.yunhorn.core.chirpstack.client.response.gateway.GatewayGetInfoResp;
import com.yunhorn.core.chirpstack.client.response.gateway.GatewayGetResp;
import com.yunhorn.core.chirpstack.client.response.gateway.GatewayGetResult;
import com.yunhorn.core.chirpstack.client.response.gatewayprofile.GatewayProfileGetInfoResp;
import com.yunhorn.core.chirpstack.client.response.gatewayprofile.GatewayProfileGetResp;
import com.yunhorn.core.chirpstack.client.response.gatewayprofile.GatewayProfileGetResult;
import com.yunhorn.core.chirpstack.client.response.gatewayprofile.GatewayProfilePostResp;
import com.yunhorn.core.chirpstack.client.response.networkserver.NetworkServerGetInfoResp;
import com.yunhorn.core.chirpstack.client.response.networkserver.NetworkServerGetResp;
import com.yunhorn.core.chirpstack.client.response.networkserver.NetworkServerGetResult;
import com.yunhorn.core.chirpstack.client.response.organization.OrganizationGetResp;
import com.yunhorn.core.chirpstack.client.response.organization.OrganizationGetResult;
import com.yunhorn.core.chirpstack.client.response.serviceprofile.ServiceProfileGetInfoResp;
import com.yunhorn.core.chirpstack.client.response.serviceprofile.ServiceProfileGetResp;
import com.yunhorn.core.chirpstack.client.response.serviceprofile.ServiceProfileGetResult;
import com.yunhorn.core.chirpstack.client.response.serviceprofile.ServiceProfilePostResp;
import com.yunhorn.core.chirpstack.dto.ApplicationSyncReq;
import com.yunhorn.core.chirpstack.dto.DeviceSyncReq;
import com.yunhorn.core.chirpstack.dto.GatewaySyncReq;
import com.yunhorn.core.chirpstack.dto.SyncReq;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ljm
 * @date 2021/12/23 11:25
 */
@Service
@Slf4j
public class SyncService {

    @Autowired
    private ApplicationServiceLoraWanHttp applicationServiceLoraWanHttp;

    @Autowired
    private OrganizationServiceLoraWanHttp organizationServiceLoraWanHttp;

    @Autowired
    private ServiceProfileServiceLoraWanHttp serviceProfileServiceLoraWanHttp;

    @Autowired
    private DeviceServiceLoraWanHttp deviceServiceLoraWanHttp;

    @Autowired
    private DeviceProfileServiceLoraWanHttp deviceProfileServiceLoraWanHttp;

    @Autowired
    private GatewayServiceLoraWanHttp gatewayServiceLoraWanHttp;

    @Autowired
    private GatewayProfileServiceLoraWanHttp gatewayProfileServiceLoraWanHttp;

    @Autowired
    private NetworkServerServiceLoraWanHttp networkServerServiceLoraWanHttp;

    public void syncApplication(ApplicationSyncReq applicationSyncReq){
        try {
            log.info("Begin syncApplication...|reqObject:{}",JSONUtils.beanToJson(applicationSyncReq));
            String sourceDomain = applicationSyncReq.getSourceDomain();
            String sourceAccount = applicationSyncReq.getSourceAccount();
            String sourcePassword = applicationSyncReq.getSourcePassword();
            String targetDomain = applicationSyncReq.getTargetDomain();
            String targetAccount = applicationSyncReq.getTargetAccount();
            String targetPassword = applicationSyncReq.getTargetPassword();
            String sourceOrganizationName = applicationSyncReq.getSourceOrganizationName();
            String targetOrganizationName = applicationSyncReq.getTargetOrganizationName();
            String targetServiceProfileName = applicationSyncReq.getTargetServiceProfileName();
            OrganizationGetResp sourceOrganizationGetResp = organizationServiceLoraWanHttp.get(new OrganizationGetReq(),sourceDomain,sourceAccount,sourcePassword);
            String sourceOrganizationID = sourceOrganizationGetResp.getResult().stream().filter(organizationGetResult -> sourceOrganizationName.equals(organizationGetResult.getName())).findFirst().map(OrganizationGetResult::getId).orElse(null);
            OrganizationGetResp targetOrganizationGetResp = organizationServiceLoraWanHttp.get(new OrganizationGetReq(),targetDomain,targetAccount,targetPassword);
            String targetOrganizationID = targetOrganizationGetResp.getResult().stream().filter(organizationGetResult -> targetOrganizationName.equals(organizationGetResult.getName())).findFirst().map(OrganizationGetResult::getId).orElse(null);
            if (sourceOrganizationID==null){
                log.error("SourceOrganizationName Can't find the corresponding id");
                return;
            }else if (targetOrganizationID==null){
                log.error("TargetOrganizationName Can't find the corresponding id");
                return;
            }
            ServiceProfileGetResp targetServiceProfileGetResp = serviceProfileServiceLoraWanHttp.get(new ServiceProfileGetReq(targetOrganizationID),targetDomain,targetAccount,targetPassword);
            String targetServiceProfileId = targetServiceProfileGetResp.getResult().stream().filter(serviceProfileGetResult -> targetServiceProfileName.equals(serviceProfileGetResult.getName())).findFirst().map(ServiceProfileGetResult::getId).orElse(null);
            if (targetServiceProfileId==null){
                log.error("TargetServiceProfileName Can't find the corresponding id");
                return;
            }
            ApplicationsGetResp sourceApplicationsResp = applicationServiceLoraWanHttp.get(new ApplicationsGetReq(sourceOrganizationID),sourceDomain,sourceAccount,sourcePassword);//查询源chirpStack的application数据
            ApplicationsGetResp targetApplicationsResp = applicationServiceLoraWanHttp.get(new ApplicationsGetReq(targetOrganizationID),targetDomain,targetAccount,targetPassword);//查询需要同步的目标chirpStack的application数据

            //比较name 如果name相同 则认为是同一application 需要进行比较
            Map<String,ApplicationsGetResult> targetApplicationMap = targetApplicationsResp.getResult().stream().collect(Collectors.toMap(ApplicationsGetResult::getName, Function.identity()));
            int updateCount = 0;
            List<String> updateApplicationNames = Lists.newArrayList();
            int insertCount = 0;
            List<String> insertApplicationNames = Lists.newArrayList();
            for (ApplicationsGetResult sourceApplicationsGetResult : sourceApplicationsResp.getResult()) {
                ApplicationsGetResult targetApplicationGetResult = targetApplicationMap.get(sourceApplicationsGetResult.getName());
                ApplicationsGetInfoResp sourceApplicationInfo = applicationServiceLoraWanHttp.get(sourceApplicationsGetResult.getId(),sourceDomain,sourceAccount,sourcePassword);
                if (targetApplicationGetResult!=null){
                    ApplicationsGetInfoResp targetApplicationInfo = applicationServiceLoraWanHttp.get(targetApplicationGetResult.getId(),targetDomain,targetAccount,targetPassword);
                    Application sourceApplication = sourceApplicationInfo.getApplication();
                    Application targetApplicationOriginal = targetApplicationInfo.getApplication();
                    if (!sourceApplication.equals(targetApplicationOriginal)){
                        Application targetApplication = sourceApplication.copyProperties(targetOrganizationID,targetServiceProfileId,targetApplicationOriginal.getId());
                        applicationServiceLoraWanHttp.put(new ApplicationsPutReq(targetApplication),targetDomain,targetAccount,targetPassword);
                        log.info("Update target chirpStack Application|Before update:{}|After update:{}", JSONUtils.beanToJson(targetApplicationOriginal),JSONUtils.beanToJson(targetApplication));
                        updateCount ++;
                        updateApplicationNames.add(targetApplicationGetResult.getName());
                    }
                }else {
                    Application targetApplication = sourceApplicationInfo.getApplication().copyProperties(targetOrganizationID,targetServiceProfileId,null);
                    ApplicationsPostResp applicationsPostResp = applicationServiceLoraWanHttp.post(new ApplicationsPostReq(targetApplication),targetDomain,targetAccount,targetPassword);
                    log.info("Insert Application to target chirpStack|insertObj:{}|resp:{}",JSONUtils.beanToJson(targetApplication),JSONUtils.beanToJson(applicationsPostResp));
                    insertCount ++;
                    insertApplicationNames.add(targetApplication.getName());
                }
            }
            if (updateCount>0 || insertCount>0){
                if (updateCount>0){
                    log.info("SyncApplication success! Update application count :{},their applicationNames are {} ",updateCount,JSONUtils.beanToJson(updateApplicationNames));
                }
                if (insertCount>0){
                    log.info("SyncApplication success! Insert application count :{},their applicationNames are {}",insertCount,JSONUtils.beanToJson(insertApplicationNames));
                }
            }else {
                log.info("Scan application success! No syncable application found!");
            }
        }catch (Exception e){
            log.error("syncApplication exception has occurred",e);
        }
    }

    public void syncDevice(DeviceSyncReq deviceSyncReq){
        try {
            log.info("Begin syncDevice...|reqObject:{}",JSONUtils.beanToJson(deviceSyncReq));
            String sourceDomain = deviceSyncReq.getSourceDomain();
            String sourceAccount = deviceSyncReq.getSourceAccount();
            String sourcePassword = deviceSyncReq.getSourcePassword();
            String targetDomain = deviceSyncReq.getTargetDomain();
            String targetAccount = deviceSyncReq.getTargetAccount();
            String targetPassword = deviceSyncReq.getTargetPassword();
            String sourceOrganizationName = deviceSyncReq.getSourceOrganizationName();
            String targetOrganizationName = deviceSyncReq.getTargetOrganizationName();
            String targetDeviceProfileName = deviceSyncReq.getTargetDeviceProfileName();
            OrganizationGetResp sourceOrganizationGetResp = organizationServiceLoraWanHttp.get(new OrganizationGetReq(),sourceDomain,sourceAccount,sourcePassword);
            String sourceOrganizationID = sourceOrganizationGetResp.getResult().stream().filter(organizationGetResult -> sourceOrganizationName.equals(organizationGetResult.getName())).findFirst().map(OrganizationGetResult::getId).orElse(null);
            OrganizationGetResp targetOrganizationGetResp = organizationServiceLoraWanHttp.get(new OrganizationGetReq(),targetDomain,targetAccount,targetPassword);
            String targetOrganizationID = targetOrganizationGetResp.getResult().stream().filter(organizationGetResult -> targetOrganizationName.equals(organizationGetResult.getName())).findFirst().map(OrganizationGetResult::getId).orElse(null);
            if (sourceOrganizationID==null){
                log.error("SourceOrganizationName Can't find the corresponding id");
                return;
            }else if (targetOrganizationID==null){
                log.error("TargetOrganizationName Can't find the corresponding id");
                return;
            }
            DeviceProfileGetResp targetDeviceProfileGetResp = deviceProfileServiceLoraWanHttp.get(new DeviceProfileGetReq(targetOrganizationID),targetDomain,targetAccount,targetPassword);
            String targetDeviceProfileId = targetDeviceProfileGetResp.getResult().stream().filter(targetDeviceProfileGetResult->targetDeviceProfileGetResult.getName().equals(targetDeviceProfileName)).findFirst().map(DeviceProfileGetResult::getId).orElse(null);
            if (targetDeviceProfileId==null){
                log.error("TargetDeviceProfileName Can't find the corresponding id");
                return;
            }
            ApplicationsGetResp sourceApplicationsResp = applicationServiceLoraWanHttp.get(new ApplicationsGetReq(sourceOrganizationID),sourceDomain,sourceAccount,sourcePassword);//查询源chirpStack的application数据
            ApplicationsGetResp targetApplicationsResp = applicationServiceLoraWanHttp.get(new ApplicationsGetReq(targetOrganizationID),targetDomain,targetAccount,targetPassword);//查询目标chirpStack的application数据
            Map<String,String> targetApplicationsResultMap = targetApplicationsResp.getResult().stream().collect(Collectors.toMap(ApplicationsGetResult::getName,ApplicationsGetResult::getId));
            Map<String,String> deviceSyncApplicationMap = Maps.newHashMap();//需要同步设备的application（key：源平台的applicationId，value：目标平台的applicationId）
            if (!deviceSyncReq.getApplicationNames().isEmpty()){
                Map<String,String> sourceApplicationsResultMap = sourceApplicationsResp.getResult().stream().collect(Collectors.toMap(ApplicationsGetResult::getName,ApplicationsGetResult::getId));
                for (String applicationName : deviceSyncReq.getApplicationNames()) {
                    String sourceApplicationId = sourceApplicationsResultMap.get(applicationName);
                    String targetApplicationId = targetApplicationsResultMap.get(applicationName);
                    if (sourceApplicationId == null){
                        log.error("Source organization "+sourceOrganizationName+" was not found the application named "+applicationName);
                        continue;
                    }else if (targetApplicationId == null){
                        log.error("Target organization "+targetOrganizationName+" was not found the application named "+applicationName);
                        continue;
                    }
                    deviceSyncApplicationMap.put(sourceApplicationId,targetApplicationId);
                }
            }else {
                for (ApplicationsGetResult sourceApplicationsGetResult : sourceApplicationsResp.getResult()) {
                    String sourceApplicationName = sourceApplicationsGetResult.getName();
                    String targetApplicationId = targetApplicationsResultMap.get(sourceApplicationName);
                    if (targetApplicationId!=null){
                        deviceSyncApplicationMap.put(sourceApplicationsGetResult.getId(),targetApplicationId);
                    }
                }
            }
            Map<String,String> sourceApplicationMap = sourceApplicationsResp.getResult().stream().collect(Collectors.toMap(ApplicationsGetResult::getId,ApplicationsGetResult::getName));
            Set<String> changedApplication = Sets.newHashSet();//检查之后有改动device的application
            deviceSyncApplicationMap.forEach((deviceSyncSourceApplicationId,deviceSyncTargetApplicationId)->{
                String sourceApplicationName = sourceApplicationMap.get(deviceSyncSourceApplicationId);
                int updateCount = 0;
                int insertCount = 0;
                List<String> insertEUIs = Lists.newArrayList();
                List<String> updateEUIs = Lists.newArrayList();
                DeviceGetResp sourceDeviceGetResp = deviceServiceLoraWanHttp.get(new DeviceGetReq(deviceSyncSourceApplicationId),sourceDomain,sourceAccount,sourcePassword);
                DeviceGetResp targetDeviceGetResp = deviceServiceLoraWanHttp.get(new DeviceGetReq(deviceSyncTargetApplicationId),targetDomain,targetAccount,targetPassword);
                List<String> targetDeviceIdMap = targetDeviceGetResp.getResult().stream().map(DeviceGetResult::getDevEUI).collect(Collectors.toList());
                for (DeviceGetResult sourceDeviceGetResult : sourceDeviceGetResp.getResult()) {
                    String syncDeviceId = sourceDeviceGetResult.getDevEUI();
                    DeviceGetInfoResp sourceDeviceGetInfoResp = deviceServiceLoraWanHttp.get(syncDeviceId,sourceDomain,sourceAccount,sourcePassword);
                    Device sourceDevice = sourceDeviceGetInfoResp.getDevice();
                    if (targetDeviceIdMap.contains(syncDeviceId)){
                        boolean updateDevice = false;
                        DeviceGetInfoResp targetDeviceGetInfoResp = deviceServiceLoraWanHttp.get(syncDeviceId,targetDomain,targetAccount,targetPassword);
                        Device targetDevice = targetDeviceGetInfoResp.getDevice();
                        //除了检查Device还需检查DeviceKey
                        if (!sourceDevice.equals(targetDevice)){
                            //源Device和目标Device的内容不一样
                            Device newTargetDevice = sourceDevice.copyProperties(deviceSyncTargetApplicationId,targetDeviceProfileId);
                            deviceServiceLoraWanHttp.put(new DevicePutReq(newTargetDevice),targetDomain,targetAccount,targetPassword);
                            log.info("Update target chirpStack Application's Device|Before update:{}|After update:{}", JSONUtils.beanToJson(targetDevice),JSONUtils.beanToJson(newTargetDevice));
                            updateDevice = true;
                        }
                        DeviceKeysGetResp sourceDeviceKeysGetResp = deviceServiceLoraWanHttp.getDeviceKey(sourceDevice.getDevEUI(),sourceDomain,sourceAccount,sourcePassword);
                        DeviceKeysGetResp targetDeviceKeysGetResp = deviceServiceLoraWanHttp.getDeviceKey(targetDevice.getDevEUI(),targetDomain,targetAccount,targetPassword);
                        if (sourceDeviceKeysGetResp==null && targetDeviceKeysGetResp!=null){
                            //如果源Device不存在Key但是目标Device存在Key，则把目标Device的Key删除
                            deviceServiceLoraWanHttp.deleteDeviceKey(targetDevice.getDevEUI(),targetDomain,targetAccount,targetPassword);
                            updateDevice = true;
                            log.info("Delete target chirpStack Application's Device'Key|Before delete:{}",JSONUtils.beanToJson(targetDeviceKeysGetResp.getDeviceKeys()));
                        }else if (sourceDeviceKeysGetResp!=null && targetDeviceKeysGetResp==null){
                            //如果源Device存在key但是目标Device不存在，则将源Device的key添加到目标Device的key
                            deviceServiceLoraWanHttp.postDeviceKey(new DeviceKeysPostReq(sourceDeviceKeysGetResp.getDeviceKeys()),targetDomain,targetAccount,targetPassword,true);
                            updateDevice = true;
                            log.info("Insert target chirpStack Application's Device'Key|insertObj:{}",JSONUtils.beanToJson(sourceDeviceKeysGetResp.getDeviceKeys()));
                        }else if (sourceDeviceKeysGetResp != null){
                            //如果源Device和目标Device都存在key，则对比两者的key是否内容相同，如果不同则将源DeviceKey的内容更新到目标DeviceKey
                            DeviceKeys sourceDeviceKeys = sourceDeviceKeysGetResp.getDeviceKeys();
                            DeviceKeys targetDeviceKeys = targetDeviceKeysGetResp.getDeviceKeys();
                            if (!sourceDeviceKeys.equals(targetDeviceKeys)){
                                DeviceKeys newTargetDeviceKeys = sourceDeviceKeys.copyProperties();
                                deviceServiceLoraWanHttp.putDeviceKey(new DeviceKeysPutReq(newTargetDeviceKeys),targetDomain,targetAccount,targetPassword);
                                updateDevice = true;
                                log.info("Update target chirpStack Application's Device'Key|Before update:{}|After update:{}",JSONUtils.beanToJson(targetDeviceKeys),JSONUtils.beanToJson(newTargetDeviceKeys));
                            }
                        }
                        if (updateDevice){
                            updateCount ++;
                            updateEUIs.add(syncDeviceId);
                            changedApplication.add(sourceApplicationName);
                        }
                    }else {
                        //目标application不存在该设备 添加
                        Device targetDevice = sourceDevice.copyProperties(deviceSyncTargetApplicationId,targetDeviceProfileId);
                        boolean isSuccess = deviceServiceLoraWanHttp.post(new DevicePostReq(targetDevice),targetDomain,targetAccount,targetPassword,true);
                        //如果源device有key，则将其添加到目标device的key
                        DeviceKeysGetResp sourceDeviceKeysGetResp = deviceServiceLoraWanHttp.getDeviceKey(sourceDevice.getDevEUI(),sourceDomain,sourceAccount,sourcePassword);
                        if (sourceDeviceKeysGetResp!=null && isSuccess){
                            DeviceKeys sourceDeviceKeys = sourceDeviceKeysGetResp.getDeviceKeys();
                            isSuccess = deviceServiceLoraWanHttp.postDeviceKey(new DeviceKeysPostReq(sourceDeviceKeys),targetDomain,targetAccount,targetPassword,true);
                            if (isSuccess){
                                log.info("Insert Device and DeviceKey to target chirpStack success!|insertDevice:{}|insertDeviceKey:{}",JSONUtils.beanToJson(targetDevice),JSONUtils.beanToJson(sourceDeviceKeys));
                            }else {
                                log.error("Insert Device to target chirpStack success but insert the deviceKey error|insertDeviceKey:"+JSONUtils.beanToJson(sourceDeviceKeys),
                                        new Exception("Insert Device to target chirpStack success but insert the deviceKey error"));
                            }
                        }else {
                            if (isSuccess){
                                log.info("Insert Device to target chirpStack success!|insertDevice:{}",JSONUtils.beanToJson(targetDevice));
                            }else {
                                log.error("Insert Device to target chirpStack error!|insertDevice:"+JSONUtils.beanToJson(targetDevice),new Exception("Insert Device to target chirpStack error"));
                            }
                        }
                        if (isSuccess){
                            insertCount ++;
                            insertEUIs.add(targetDevice.getDevEUI());
                            changedApplication.add(sourceApplicationName);
                        }
                    }
                }
                if (updateCount >0 || insertCount>0){
                    if (updateCount >0){
                        log.info("Sync {}'s device success! Update device count :{},their DevEUIs are {} ",sourceApplicationName,updateCount,JSONUtils.beanToJson(updateEUIs));
                    }
                    if (insertCount>0){
                        log.info("Sync {}'s device success! Insert device count :{},their DevEUIs are {}",sourceApplicationName,insertCount,JSONUtils.beanToJson(insertEUIs));
                    }
                }else {
                    log.info("Scan {}'s device success! No syncable device found!",sourceApplicationName);
                }
            });
            Set<String> checkApplications = deviceSyncApplicationMap.keySet().stream().map(sourceApplicationMap::get).collect(Collectors.toSet());
            Set<String> unCheckApplications = Sets.difference(sourceApplicationsResp.getResult().stream().map(ApplicationsGetResult::getName).collect(Collectors.toSet()),checkApplications);
            log.info("End syncDevice! Scanned the devices in the following application on the source chirpStack and target chirpStack:{}|Devices with the following applications are not scanned on the source chirpStack:{}|After scanning, the devices with the following applications have been changed:{}",
                    JSONUtils.beanToJson(checkApplications),JSONUtils.beanToJson(unCheckApplications),JSONUtils.beanToJson(changedApplication));
        }catch (Exception e){
            log.error("syncDevice exception has occurred",e);
        }
    }

    public void syncGateway(GatewaySyncReq gatewaySyncReq){
        try {
            log.info("Begin syncGateway|reqObject:{}",JSONUtils.beanToJson(gatewaySyncReq));
            String sourceDomain = gatewaySyncReq.getSourceDomain();
            String sourceAccount = gatewaySyncReq.getSourceAccount();
            String sourcePassword = gatewaySyncReq.getSourcePassword();
            String targetDomain = gatewaySyncReq.getTargetDomain();
            String targetAccount = gatewaySyncReq.getTargetAccount();
            String targetPassword = gatewaySyncReq.getTargetPassword();
            String sourceOrganizationName = gatewaySyncReq.getSourceOrganizationName();
            String targetOrganizationName = gatewaySyncReq.getTargetOrganizationName();
            OrganizationGetResp sourceOrganizationGetResp = organizationServiceLoraWanHttp.get(new OrganizationGetReq(),sourceDomain,sourceAccount,sourcePassword);
            String sourceOrganizationID = sourceOrganizationGetResp.getResult().stream().filter(organizationGetResult -> sourceOrganizationName.equals(organizationGetResult.getName())).findFirst().map(OrganizationGetResult::getId).orElse(null);
            OrganizationGetResp targetOrganizationGetResp = organizationServiceLoraWanHttp.get(new OrganizationGetReq(),targetDomain,targetAccount,targetPassword);
            String targetOrganizationID = targetOrganizationGetResp.getResult().stream().filter(organizationGetResult -> targetOrganizationName.equals(organizationGetResult.getName())).findFirst().map(OrganizationGetResult::getId).orElse(null);
            if (sourceOrganizationID==null){
                log.error("SourceOrganizationName Can't find the corresponding id");
                return;
            }else if (targetOrganizationID==null){
                log.error("TargetOrganizationName Can't find the corresponding id");
                return;
            }
            checkServiceProfiles(gatewaySyncReq,sourceOrganizationID,targetOrganizationID);
            checkGatewayProfile(gatewaySyncReq,sourceOrganizationID,targetOrganizationID);
            GatewayGetResp sourceGatewayGetResp = gatewayServiceLoraWanHttp.get(new GatewayGetReq(sourceOrganizationID),sourceDomain,sourceAccount,sourcePassword);
            GatewayGetResp targetGatewayGetResp = gatewayServiceLoraWanHttp.get(new GatewayGetReq(targetOrganizationID),targetDomain,targetAccount,targetPassword);
            Map<String,String> targetGatewayNameMap = targetGatewayGetResp.getResult().stream().collect(Collectors.toMap(GatewayGetResult::getName,GatewayGetResult::getId));
            NetworkServerGetResp sourceNetworkServerGetResp = networkServerServiceLoraWanHttp.get(new NetworkServerGetReq(sourceOrganizationID),sourceDomain,sourceAccount,sourcePassword);
            NetworkServerGetResp targetNetworkServerGetResp = networkServerServiceLoraWanHttp.get(new NetworkServerGetReq(targetOrganizationID),targetDomain,targetAccount,targetPassword);
            Map<String,String> sourceNetworkServerIdMap = sourceNetworkServerGetResp.getResult().stream().collect(Collectors.toMap(NetworkServerGetResult::getId,NetworkServerGetResult::getName));
            Map<String,String> targetNetworkServerNameMap = targetNetworkServerGetResp.getResult().stream().collect(Collectors.toMap(NetworkServerGetResult::getName,NetworkServerGetResult::getId));
            ServiceProfileGetResp sourceServiceProfileGetResp = serviceProfileServiceLoraWanHttp.get(new ServiceProfileGetReq(sourceOrganizationID),sourceDomain,sourceAccount,sourcePassword);
            ServiceProfileGetResp targetServiceProfileGetResp = serviceProfileServiceLoraWanHttp.get(new ServiceProfileGetReq(targetOrganizationID),targetDomain,targetAccount,targetPassword);
            Map<String,String> sourceServiceProfileIdMap = sourceServiceProfileGetResp.getResult().stream().collect(Collectors.toMap(ServiceProfileGetResult::getId,ServiceProfileGetResult::getName));
            Map<String,String> targetServiceProfileNameMap = targetServiceProfileGetResp.getResult().stream().collect(Collectors.toMap(ServiceProfileGetResult::getName,ServiceProfileGetResult::getId));
            GatewayProfileGetResp sourceGatewayProfileGetResp = gatewayProfileServiceLoraWanHttp.get(new GatewayProfileGetReq(),gatewaySyncReq.getSourceDomain(),gatewaySyncReq.getSourceAccount(),gatewaySyncReq.getSourcePassword());
            GatewayProfileGetResp targetGatewayProfileGetResp = gatewayProfileServiceLoraWanHttp.get(new GatewayProfileGetReq(),gatewaySyncReq.getTargetDomain(),gatewaySyncReq.getTargetAccount(),gatewaySyncReq.getTargetPassword());
            Map<String,String> targetGatewayProfileNameMap = targetGatewayProfileGetResp.getResult().stream().collect(Collectors.toMap(GatewayProfileGetResult::getName,GatewayProfileGetResult::getId));
            Map<String,String> sourceGatewayProfileIdMap = sourceGatewayProfileGetResp.getResult().stream().collect(Collectors.toMap(GatewayProfileGetResult::getId,GatewayProfileGetResult::getName));
            for (GatewayGetResult sourceGatewayGetResult : sourceGatewayGetResp.getResult()) {
                String targetGatewayId = targetGatewayNameMap.get(sourceGatewayGetResult.getName());
                String sourceGatewayId = sourceGatewayGetResult.getId();
                GatewayGetInfoResp sourceGatewayGetInfoResp = gatewayServiceLoraWanHttp.get(sourceGatewayId,sourceDomain,sourceAccount,sourcePassword);
                Gateway sourceGateway = sourceGatewayGetInfoResp.getGateway();
                String sourceNetworkServerName = sourceNetworkServerIdMap.get(sourceGateway.getNetworkServerID());
                String targetNetworkServerId = targetNetworkServerNameMap.get(sourceNetworkServerName);
                if (targetNetworkServerId==null){
                    log.error("The target chirpStack lacks the NetworkServer named "+sourceNetworkServerName+", please synchronize the NetworkServer first");
                    continue;
                }
                String sourceServiceProfileName = sourceServiceProfileIdMap.get(sourceGateway.getServiceProfileID());
                String targetServiceProfileId = targetServiceProfileNameMap.get(sourceServiceProfileName);
                String sourceGatewayProfileName = sourceGatewayProfileIdMap.get(sourceGateway.getGatewayProfileID());
                String targetGatewayProfileId = targetGatewayProfileNameMap.get(sourceGatewayProfileName);
                if (targetGatewayId!=null){
                    GatewayGetInfoResp targetGatewayGetInfoResp = gatewayServiceLoraWanHttp.get(targetGatewayId,targetDomain,targetAccount,targetPassword);
                    Gateway targetGateway = targetGatewayGetInfoResp.getGateway();
                    if (!sourceGateway.equals(targetGateway)){
                        Gateway targetGatewayCopy = sourceGateway.copyProperties(targetGateway.getOrganizationID(),targetNetworkServerId,targetServiceProfileId,targetGatewayProfileId);
                        gatewayServiceLoraWanHttp.put(new GatewayPutReq(targetGatewayCopy),targetDomain,targetAccount,targetPassword);
                        log.info("Update target chirpStack Gateway|Before update:{}|After update:{}", JSONUtils.beanToJson(targetGateway),JSONUtils.beanToJson(targetGatewayCopy));
                    }
                }else {
                    Gateway targetGatewayCopy = sourceGateway.copyProperties(targetOrganizationID,targetNetworkServerId,targetServiceProfileId,targetGatewayProfileId);
                    boolean isSuccess = gatewayServiceLoraWanHttp.post(targetDomain,targetAccount,targetPassword,new GatewayPostReq(targetGatewayCopy));
                    log.info("Insert Gateway to target chirpStack|insertObj:{}|isSuccess:{}",JSONUtils.beanToJson(targetGatewayCopy),isSuccess);
                }
            }
            log.info("End syncGateway! ");
        }catch (Exception e){
            log.error("syncGateway exception has occurred",e);
        }
    }

    public void checkServiceProfiles(SyncReq syncReq,String sourceOrganizationID,String targetOrganizationID){
        ServiceProfileGetResp sourceServiceProfileGetResp = serviceProfileServiceLoraWanHttp.get(new ServiceProfileGetReq(sourceOrganizationID),syncReq.getSourceDomain(),syncReq.getSourceAccount(),syncReq.getSourcePassword());
        ServiceProfileGetResp targetServiceProfileGetResp = serviceProfileServiceLoraWanHttp.get(new ServiceProfileGetReq(targetOrganizationID),syncReq.getTargetDomain(),syncReq.getTargetAccount(),syncReq.getTargetPassword());
        Map<String,String> sourceServiceProfileMap = sourceServiceProfileGetResp.getResult().stream().collect(Collectors.toMap(ServiceProfileGetResult::getName,ServiceProfileGetResult::getId));
        Map<String,String> targetServiceProfileMap = targetServiceProfileGetResp.getResult().stream().collect(Collectors.toMap(ServiceProfileGetResult::getName,ServiceProfileGetResult::getId));
        NetworkServerGetResp targetNetworkServerGetResp = networkServerServiceLoraWanHttp.get(new NetworkServerGetReq(targetOrganizationID),syncReq.getTargetDomain(),syncReq.getTargetAccount(),syncReq.getTargetPassword());
        Map<String,String> targetNetworkServerNameMap = targetNetworkServerGetResp.getResult().stream().collect(Collectors.toMap(NetworkServerGetResult::getName,NetworkServerGetResult::getId));
        NetworkServerGetResp sourceNetworkServerGetResp = networkServerServiceLoraWanHttp.get(new NetworkServerGetReq(sourceOrganizationID),syncReq.getSourceDomain(),syncReq.getSourceAccount(),syncReq.getSourcePassword());
        Map<String,String> sourceNetworkServerIdMap = sourceNetworkServerGetResp.getResult().stream().collect(Collectors.toMap(NetworkServerGetResult::getId,NetworkServerGetResult::getName));
        for (String sourceServiceProfileName : sourceServiceProfileMap.keySet()) {
            String sourceServiceProfileId = sourceServiceProfileMap.get(sourceServiceProfileName);
            ServiceProfileGetInfoResp sourceServiceProfileGetInfoResp = serviceProfileServiceLoraWanHttp.get(sourceServiceProfileId,syncReq.getSourceDomain(),syncReq.getSourceAccount(),syncReq.getSourcePassword());
            ServiceProfile sourceServiceProfile = sourceServiceProfileGetInfoResp.getServiceProfile();
            String sourceNetworkServerId = sourceServiceProfile.getNetworkServerID();
            String sourceNetworkServerName = sourceNetworkServerIdMap.get(sourceNetworkServerId);
            if (sourceNetworkServerName==null){
                continue;
            }
            String targetNetworkServerId = targetNetworkServerNameMap.get(sourceNetworkServerName);
            if (targetNetworkServerId==null){
                continue;
            }
            if (targetServiceProfileMap.containsKey(sourceServiceProfileName)){
                //查看是否需要更新
                String targetServiceProfileId = targetServiceProfileMap.get(sourceServiceProfileName);
                ServiceProfileGetInfoResp targetServiceProfileGetInfoResp = serviceProfileServiceLoraWanHttp.get(targetServiceProfileId,syncReq.getTargetDomain(),syncReq.getTargetAccount(),syncReq.getTargetPassword());
                ServiceProfile targetServiceProfile = targetServiceProfileGetInfoResp.getServiceProfile();
                if (!sourceServiceProfile.equals(targetServiceProfile)){
                    ServiceProfile ServiceProfileCopy = sourceServiceProfile.copyProperties(targetOrganizationID,targetNetworkServerId,targetServiceProfile.getId());
                    serviceProfileServiceLoraWanHttp.put(new ServiceProfilePutReq(ServiceProfileCopy),syncReq.getTargetDomain(),syncReq.getTargetAccount(),syncReq.getTargetPassword());
                    log.info("Update target chirpStack serviceProfiles|Before update:{}|After update:{}", JSONUtils.beanToJson(targetServiceProfile),JSONUtils.beanToJson(ServiceProfileCopy));
                }
            }else {
                //目标不存在ServiceProfiles，插入
                ServiceProfile ServiceProfileCopy = sourceServiceProfile.copyProperties(targetOrganizationID,targetNetworkServerId,null);
                ServiceProfilePostResp serviceProfilePostResp = serviceProfileServiceLoraWanHttp.post(syncReq.getTargetDomain(),syncReq.getTargetAccount(),syncReq.getTargetPassword(),new ServiceProfilePostReq(ServiceProfileCopy));
                log.info("Insert serviceProfiles to target chirpStack|insertObj:{}|resp:{}",JSONUtils.beanToJson(ServiceProfileCopy),JSONUtils.beanToJson(serviceProfilePostResp));
            }
        }
    }

    public void checkGatewayProfile(SyncReq syncReq,String sourceOrganizationID,String targetOrganizationID){
        GatewayProfileGetResp sourceGatewayProfileGetResp = gatewayProfileServiceLoraWanHttp.get(new GatewayProfileGetReq(),syncReq.getSourceDomain(),syncReq.getSourceAccount(),syncReq.getSourcePassword());
        GatewayProfileGetResp targetGatewayProfileGetResp = gatewayProfileServiceLoraWanHttp.get(new GatewayProfileGetReq(),syncReq.getTargetDomain(),syncReq.getTargetAccount(),syncReq.getTargetPassword());
        NetworkServerGetResp targetNetworkServerGetResp = networkServerServiceLoraWanHttp.get(new NetworkServerGetReq(targetOrganizationID),syncReq.getTargetDomain(),syncReq.getTargetAccount(),syncReq.getTargetPassword());
        Map<String,String> targetNetworkServerNameMap = targetNetworkServerGetResp.getResult().stream().collect(Collectors.toMap(NetworkServerGetResult::getName,NetworkServerGetResult::getId));
        Map<String,String> targetGatewayProfileNameMap = targetGatewayProfileGetResp.getResult().stream().collect(Collectors.toMap(GatewayProfileGetResult::getName,GatewayProfileGetResult::getId));
        NetworkServerGetResp sourceNetworkServerGetResp = networkServerServiceLoraWanHttp.get(new NetworkServerGetReq(sourceOrganizationID),syncReq.getSourceDomain(),syncReq.getSourceAccount(),syncReq.getSourcePassword());
        Map<String,String> sourceNetworkServerIdMap = sourceNetworkServerGetResp.getResult().stream().collect(Collectors.toMap(NetworkServerGetResult::getId,NetworkServerGetResult::getName));
        for (GatewayProfileGetResult sourceGatewayProfileGetResult : sourceGatewayProfileGetResp.getResult()) {
            GatewayProfileGetInfoResp sourceGatewayProfileGetInfoResp = gatewayProfileServiceLoraWanHttp.get(sourceGatewayProfileGetResult.getId(),syncReq.getSourceDomain(),syncReq.getSourceAccount(),syncReq.getSourcePassword());
            GatewayProfile sourceGatewayProfile = sourceGatewayProfileGetInfoResp.getGatewayProfile();
            String sourceGatewayProfileName = sourceGatewayProfileGetResult.getName();
            String targetGatewayProfileId = targetGatewayProfileNameMap.get(sourceGatewayProfileName);
            String sourceNetworkServerId = sourceGatewayProfileGetResult.getNetworkServerID();
            String sourceNetworkServerName = sourceNetworkServerIdMap.get(sourceNetworkServerId);
            if (sourceNetworkServerName==null){
                continue;
            }
            String targetNetworkServerId = targetNetworkServerNameMap.get(sourceNetworkServerName);
            if (targetNetworkServerId==null){
                continue;
            }
            if (targetGatewayProfileId!=null){
                GatewayProfileGetInfoResp targetGatewayProfileGetInfoResp = gatewayProfileServiceLoraWanHttp.get(targetGatewayProfileId,syncReq.getTargetDomain(),syncReq.getTargetAccount(),syncReq.getTargetPassword());
                GatewayProfile targetGatewayProfile = targetGatewayProfileGetInfoResp.getGatewayProfile();
                if (!sourceGatewayProfile.equals(targetGatewayProfile)){
                    GatewayProfile gatewayProfileCopy = sourceGatewayProfile.copyProperties(targetNetworkServerId,targetGatewayProfile.getId());
                    gatewayProfileServiceLoraWanHttp.put(syncReq.getTargetDomain(),syncReq.getTargetAccount(),syncReq.getTargetPassword(),new GatewayProfilePutReq(gatewayProfileCopy));
                    log.info("Update target chirpStack gatewayProfile|Before update:{}|After update:{}", JSONUtils.beanToJson(targetGatewayProfile),JSONUtils.beanToJson(gatewayProfileCopy));
                }
            }else {
                GatewayProfile gatewayProfileCopy = sourceGatewayProfile.copyProperties(targetNetworkServerId,null);
                GatewayProfilePostResp gatewayProfilePostResp = gatewayProfileServiceLoraWanHttp.post(syncReq.getTargetDomain(),syncReq.getTargetAccount(),syncReq.getTargetPassword(),new GatewayProfilePostReq(gatewayProfileCopy));
                log.info("Insert gatewayProfile to target chirpStack|insertObj:{}|resp:{}",JSONUtils.beanToJson(gatewayProfileCopy),JSONUtils.beanToJson(gatewayProfilePostResp));
            }
        }
    }
}
