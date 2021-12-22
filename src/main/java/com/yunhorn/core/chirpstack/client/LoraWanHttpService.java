//package io.github.yunhorn.chirpstackappsyncer.client;
//
//import com.smartoilets.api.service.lorawan.api.*;
//import com.smartoilets.api.service.lorawan.request.application.Application;
//import com.smartoilets.api.service.lorawan.request.application.ApplicationsGetReq;
//import com.smartoilets.api.service.lorawan.request.application.ApplicationsPostReq;
//import com.smartoilets.api.service.lorawan.request.device.Device;
//import com.smartoilets.api.service.lorawan.request.device.DeviceGetReq;
//import com.smartoilets.api.service.lorawan.request.device.DeviceKeysPostReq;
//import com.smartoilets.api.service.lorawan.request.device.DevicePostReq;
//import com.smartoilets.api.service.lorawan.request.deviceprofile.DeviceProfile;
//import com.smartoilets.api.service.lorawan.request.deviceprofile.DeviceProfileGetReq;
//import com.smartoilets.api.service.lorawan.request.deviceprofile.DeviceProfilePostReq;
//import com.smartoilets.api.service.lorawan.request.gateway.Gateway;
//import com.smartoilets.api.service.lorawan.request.gateway.GatewayGetReq;
//import com.smartoilets.api.service.lorawan.request.gateway.GatewayPostReq;
//import com.smartoilets.api.service.lorawan.request.gatewayprofile.GatewayProfile;
//import com.smartoilets.api.service.lorawan.request.gatewayprofile.GatewayProfileGetReq;
//import com.smartoilets.api.service.lorawan.request.gatewayprofile.GatewayProfilePostReq;
//import com.smartoilets.api.service.lorawan.request.networkserver.NetworkServer;
//import com.smartoilets.api.service.lorawan.request.networkserver.NetworkServerGetReq;
//import com.smartoilets.api.service.lorawan.request.networkserver.NetworkServerPostReq;
//import com.smartoilets.api.service.lorawan.request.organization.Organization;
//import com.smartoilets.api.service.lorawan.request.organization.OrganizationGetReq;
//import com.smartoilets.api.service.lorawan.request.organization.OrganizationPostReq;
//import com.smartoilets.api.service.lorawan.request.serviceprofile.ServiceProfile;
//import com.smartoilets.api.service.lorawan.request.serviceprofile.ServiceProfileGetReq;
//import com.smartoilets.api.service.lorawan.request.serviceprofile.ServiceProfilePostReq;
//import com.smartoilets.api.service.lorawan.response.application.ApplicationsGetInfoResp;
//import com.smartoilets.api.service.lorawan.response.application.ApplicationsGetResp;
//import com.smartoilets.api.service.lorawan.response.application.ApplicationsGetResult;
//import com.smartoilets.api.service.lorawan.response.device.DeviceGetInfoResp;
//import com.smartoilets.api.service.lorawan.response.device.DeviceGetResp;
//import com.smartoilets.api.service.lorawan.response.device.DeviceGetResult;
//import com.smartoilets.api.service.lorawan.response.device.DeviceKeysGetResp;
//import com.smartoilets.api.service.lorawan.response.deviceprofile.DeviceProfileGetInfoResp;
//import com.smartoilets.api.service.lorawan.response.deviceprofile.DeviceProfileGetResp;
//import com.smartoilets.api.service.lorawan.response.deviceprofile.DeviceProfileGetResult;
//import com.smartoilets.api.service.lorawan.response.gateway.GatewayGetInfoResp;
//import com.smartoilets.api.service.lorawan.response.gateway.GatewayGetResp;
//import com.smartoilets.api.service.lorawan.response.gateway.GatewayGetResult;
//import com.smartoilets.api.service.lorawan.response.gatewayprofile.GatewayProfileGetInfoResp;
//import com.smartoilets.api.service.lorawan.response.gatewayprofile.GatewayProfileGetResp;
//import com.smartoilets.api.service.lorawan.response.gatewayprofile.GatewayProfileGetResult;
//import com.smartoilets.api.service.lorawan.response.networkserver.NetworkServerGetInfoResp;
//import com.smartoilets.api.service.lorawan.response.networkserver.NetworkServerGetResp;
//import com.smartoilets.api.service.lorawan.response.networkserver.NetworkServerGetResult;
//import com.smartoilets.api.service.lorawan.response.organization.OrganizationGetResp;
//import com.smartoilets.api.service.lorawan.response.organization.OrganizationGetResult;
//import com.smartoilets.api.service.lorawan.response.serviceprofile.ServiceProfileGetInfoResp;
//import com.smartoilets.api.service.lorawan.response.serviceprofile.ServiceProfileGetResp;
//import com.smartoilets.api.service.lorawan.response.serviceprofile.ServiceProfileGetResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Comparator;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
///**
// * @author ljm
// * @date 2021/2/24 11:49
// */
//@Service
//@Slf4j
//public class LoraWanHttpService {
//
//    private static String domainOld = "http://smartoilets.cn:8087";
//    private static String domainNew = "http://192.168.3.122:8081";
//    private static String account = "admin";
//    private static String password = "admin";
//
//    @Autowired
//    private OrganizationServiceLoraWanHttp organizationService;
//
//    @Autowired
//    private NetworkServerServiceLoraWanHttp networkServerServiceLoraWanHttp;
//
//    @Autowired
//    private GatewayProfileServiceLoraWanHttp gatewayProfileServiceLoraWanHttp;
//
//    @Autowired
//    private GatewayServiceLoraWanHttp gatewayServiceLoraWanHttp;
//
//    @Autowired
//    private ServiceProfileServiceLoraWanHttp serviceProfileServiceLoraWanHttp;
//
//    @Autowired
//    private ApplicationServiceLoraWanHttp applicationService;
//
//    @Autowired
//    private DeviceProfileServiceLoraWanHttp deviceProfileServiceLoraWanHttp;
//
//    @Autowired
//    private DeviceServiceLoraWanHttp deviceServiceLoraWanHttp;
//
//    public void transferOrganization(){
//        OrganizationGetReq organizationGetReq = new OrganizationGetReq();
//        organizationGetReq.setOffset("0");
//        organizationGetReq.setLimit("10");
//        OrganizationGetResp organizationGetResp = organizationService.get(organizationGetReq,domainOld,account,password);
//        for (OrganizationGetResult organizationGetResult : organizationGetResp.getResult()) {
//            OrganizationPostReq organizationPostReq = new OrganizationPostReq();
//            Organization organization = new Organization();
//            String id = organizationGetResult.getId();
//            String name = organizationGetResult.getName();
//            String displayName = organizationGetResult.getDisplayName();
//            boolean canHaveGateways = organizationGetResult.isCanHaveGateways();
//            organization.setId(id);
//            organization.setName(name);
//            organization.setDisplayName(displayName);
//            organization.setCanHaveGateways(canHaveGateways);
//            organizationPostReq.setOrganization(organization);
//            organizationService.post(domainNew,account,password,organizationPostReq);
//        }
//    }
//
//    public void transferNetworkServer(){
//        OrganizationGetReq organizationGetReq = new OrganizationGetReq();
//        organizationGetReq.setOffset("0");
//        organizationGetReq.setLimit("10");
//        OrganizationGetResp organizationGetResp = organizationService.get(organizationGetReq,domainOld,account,password);
//        for (OrganizationGetResult organizationGetResult : organizationGetResp.getResult()) {
//            if (!"loraserver".equals(organizationGetResult.getName())){
//                continue;
//            }
//            NetworkServerGetReq networkServerGetReq = new NetworkServerGetReq();
//            networkServerGetReq.setLimit("10");
//            networkServerGetReq.setOffset("0");
//            networkServerGetReq.setOrganizationID(organizationGetResult.getId());
//            NetworkServerGetResp networkServerGetResp = networkServerServiceLoraWanHttp.get(networkServerGetReq,domainOld,account,password);
//            for (NetworkServerGetResult networkServerGetResult : networkServerGetResp.getResult()) {
//                String id = networkServerGetResult.getId();
//                String name = networkServerGetResult.getName();
//                String server = networkServerGetResult.getServer();
//                NetworkServerPostReq networkServerPostReq = new NetworkServerPostReq();
//                NetworkServerGetInfoResp networkServerGetInfoResp = networkServerServiceLoraWanHttp.get(id,domainOld,account,password);
//                NetworkServer networkServer = networkServerGetInfoResp.getNetworkServer();
////                NetworkServer networkServer = new NetworkServer();
////                networkServer.setId(id);
//                networkServer.setServer("chirpstack-network-server:8000");
////                networkServer.setServer(server);
//                networkServerPostReq.setNetworkServer(networkServer);
//                networkServerServiceLoraWanHttp.post(domainNew,account,password,networkServerPostReq);
//                return;
//            }
//        }
//    }
//
//    public void transferGatewayProfile(){
//        GatewayProfileGetReq gatewayProfileGetReq = new GatewayProfileGetReq();
//        gatewayProfileGetReq.setLimit("100");
//        gatewayProfileGetReq.setOffset("0");
//            NetworkServerGetReq networkServerGetReq = new NetworkServerGetReq();
//            networkServerGetReq.setLimit("10");
//            networkServerGetReq.setOffset("0");
//            NetworkServerGetResp networkServerGetResp = networkServerServiceLoraWanHttp.get(networkServerGetReq,domainOld,account,password);
//            NetworkServerGetResp networkServerGetRespNew = networkServerServiceLoraWanHttp.get(networkServerGetReq,domainNew,account,password);
//            for (NetworkServerGetResult networkServerGetResult : networkServerGetResp.getResult()) {
//                String networkServerId = networkServerGetResult.getId();
//                gatewayProfileGetReq.setNetworkServerID(networkServerId);
//                GatewayProfileGetResp gatewayProfileGetResp = gatewayProfileServiceLoraWanHttp.get(gatewayProfileGetReq,domainOld,account,password);
//                for (GatewayProfileGetResult gatewayProfileGetResult : gatewayProfileGetResp.getResult()) {
//                    String id = gatewayProfileGetResult.getId();
//                    String name = gatewayProfileGetResult.getName();
//                    GatewayProfileGetInfoResp gatewayProfileGetInfoResp = gatewayProfileServiceLoraWanHttp.get(id,domainOld,account,password);
//                    GatewayProfilePostReq gatewayProfilePostReq = new GatewayProfilePostReq();
//                    GatewayProfile gatewayProfile = new GatewayProfile();
//                    gatewayProfile.setId(id);
//                    gatewayProfile.setName(name);
//                    Optional<NetworkServerGetResult> optional = networkServerGetRespNew.getResult().stream().filter(networkServer->networkServer.getName().equals(networkServerGetResult.getName())).findFirst();
//                    if (optional.isPresent()){
//                        NetworkServerGetResult networkServerGetResultNew = optional.get();
//                        gatewayProfile.setNetworkServerID(networkServerGetResultNew.getId());
//                    }else {
//                        continue;
//                    }
//                    gatewayProfile.setChannels(gatewayProfileGetInfoResp.getGatewayProfile().getChannels());
//                    gatewayProfile.setExtraChannels(gatewayProfileGetInfoResp.getGatewayProfile().getExtraChannels());
//                    gatewayProfile.setStatsInterval("30s");
//                    gatewayProfilePostReq.setGatewayProfile(gatewayProfile);
//                    gatewayProfileServiceLoraWanHttp.post(domainNew,account,password,gatewayProfilePostReq);
//                }
//            }
//    }
//
//    public void transferGateway(){
//        GatewayProfileGetReq gatewayProfileGetReq = new GatewayProfileGetReq();
//        gatewayProfileGetReq.setLimit("100");
//        gatewayProfileGetReq.setOffset("0");
////        GatewayProfileGetResp gatewayProfileGetResp = gatewayProfileServiceLoraWanHttp.get(gatewayProfileGetReq,domainOld,account,password);
//        GatewayProfileGetResp gatewayProfileGetRespNew = gatewayProfileServiceLoraWanHttp.get(gatewayProfileGetReq,domainNew,account,password);
//        NetworkServerGetReq networkServerGetReq = new NetworkServerGetReq();
//        networkServerGetReq.setLimit("10");
//        networkServerGetReq.setOffset("0");
//        NetworkServerGetResp networkServerGetResp = networkServerServiceLoraWanHttp.get(networkServerGetReq,domainOld,account,password);
//        NetworkServerGetResp networkServerGetRespNew = networkServerServiceLoraWanHttp.get(networkServerGetReq,domainNew,account,password);
//        GatewayGetReq gatewayGetReq = new GatewayGetReq();
//        gatewayGetReq.setLimit("100");
//        gatewayGetReq.setOffset("0");
////        OrganizationGetReq organizationGetReq = new OrganizationGetReq();
////        organizationGetReq.setOffset("0");
////        organizationGetReq.setLimit("10");
////        OrganizationGetResp organizationGetResp = organizationService.get(organizationGetReq,domainOld,account,password);
////        for (OrganizationGetResult organizationGetResult : organizationGetResp.getResult()) {
////            gatewayGetReq.setOrganizationID(organizationGetResult.getId());
//            GatewayGetResp gatewayGetResp = gatewayServiceLoraWanHttp.get(gatewayGetReq,domainOld,account,password);
//            for (GatewayGetResult gatewayGetResult : gatewayGetResp.getResult()) {
//                String id = gatewayGetResult.getId();
//                GatewayGetInfoResp gatewayGetInfoResp = gatewayServiceLoraWanHttp.get(id,domainOld,account,password);
//                GatewayPostReq gatewayPostReq = new GatewayPostReq();
//                Gateway gateway = gatewayGetInfoResp.getGateway();
//                Optional<NetworkServerGetResult> optional = networkServerGetResp.getResult().stream().filter(networkServerGetResult -> networkServerGetResult.getId().equals(gateway.getNetworkServerID())).findFirst();
//                String newServerIdNew;
//                String gatewayProfileId;
//                if (optional.isPresent()){
//                    String nameOldNet = optional.get().getName();
//                    Optional<NetworkServerGetResult> optionalNew = networkServerGetRespNew.getResult().stream().filter(networkServerGetResult -> networkServerGetResult.getName().equals(nameOldNet)).findFirst();
//                    if (optionalNew.isPresent()){
//                        newServerIdNew = optionalNew.get().getId();
//                        Optional<GatewayProfileGetResult> getResultOptional = gatewayProfileGetRespNew.getResult().stream().filter(gatewayProfileGetResult -> gatewayProfileGetResult.getNetworkServerID().equals(newServerIdNew)).findFirst();
//                        if (getResultOptional.isPresent()){
//                            gatewayProfileId = getResultOptional.get().getId();
//                        }else {
//                            break;
//                        }
//                    }else {
//                        break;
//                    }
//                }else {
//                    break;
//                }
//                gateway.setNetworkServerID(newServerIdNew);
//                gateway.setGatewayProfileID(gatewayProfileId);
//                gateway.setOrganizationID("1");
//                gatewayPostReq.setGateway(gateway);
//                gatewayServiceLoraWanHttp.post(domainNew,account,password,gatewayPostReq);
//            }
////        }
//
//    }
//
//    public void transferServiceProfile(){
//        NetworkServerGetReq networkServerGetReq = new NetworkServerGetReq();
//        networkServerGetReq.setLimit("10");
//        networkServerGetReq.setOffset("0");
//        NetworkServerGetResp networkServerGetRespOld = networkServerServiceLoraWanHttp.get(networkServerGetReq,domainOld,account,password);
//        NetworkServerGetResp networkServerGetRespNew = networkServerServiceLoraWanHttp.get(networkServerGetReq,domainNew,account,password);
//        OrganizationGetReq organizationGetReq = new OrganizationGetReq();
//        organizationGetReq.setOffset("0");
//        organizationGetReq.setLimit("10");
//        OrganizationGetResp organizationGetResp = organizationService.get(organizationGetReq,domainOld,account,password);
//        for (OrganizationGetResult organizationGetResult : organizationGetResp.getResult()) {
//            if (!"loraserver".equals(organizationGetResult.getName())){
//                continue;
//            }
//            ServiceProfileGetReq serviceProfileGetReq = new ServiceProfileGetReq();
//            serviceProfileGetReq.setLimit("10");
//            serviceProfileGetReq.setOffset("0");
//            serviceProfileGetReq.setOrganizationID(organizationGetResult.getId());
//            ServiceProfileGetResp serviceProfileGetResp  = serviceProfileServiceLoraWanHttp.get(serviceProfileGetReq,domainOld,account,password);
//            for (ServiceProfileGetResult serviceProfileGetResult : serviceProfileGetResp.getResult()) {
//                String id = serviceProfileGetResult.getId();
//                ServiceProfileGetInfoResp serviceProfileGetInfoResp = serviceProfileServiceLoraWanHttp.get(id,domainOld,account,password);
//                String name = serviceProfileGetResult.getName();
//                String organizationID = serviceProfileGetResult.getOrganizationID();
//                String networkServerID = serviceProfileGetResult.getNetworkServerID();
//                ServiceProfilePostReq serviceProfilePostReq = new ServiceProfilePostReq();
//                ServiceProfile serviceProfile = serviceProfileGetInfoResp.getServiceProfile();
//                String networkServerIDNew;
//                Optional<NetworkServerGetResult> optionalOldNet = networkServerGetRespOld.getResult().stream().filter(networkServerGetResult -> networkServerGetResult.getId().equals(networkServerID)).findFirst();
//                if (optionalOldNet.isPresent()){
//                    String oldNetName = optionalOldNet.get().getName();
//                    Optional<NetworkServerGetResult> optionalNewNet = networkServerGetRespNew.getResult().stream().filter(networkServerGetResult -> networkServerGetResult.getName().equals(oldNetName)).findFirst();
//                    if (optionalNewNet.isPresent()){
//                        networkServerIDNew = optionalNewNet.get().getId();
//                    }else {
//                        continue;
//                    }
//                }else {
//                    continue;
//                }
//                serviceProfile.setNetworkServerID(networkServerIDNew);
//                serviceProfile.setOrganizationID("1");
//                serviceProfilePostReq.setServiceProfile(serviceProfile);
//                serviceProfileServiceLoraWanHttp.post(domainNew,account,password,serviceProfilePostReq);
//            }
//        }
//    }
//
//    public void transferApplication(){
//        ServiceProfileGetReq serviceProfileGetReq = new ServiceProfileGetReq();
//        serviceProfileGetReq.setLimit("10");
//        serviceProfileGetReq.setOffset("0");
//        ServiceProfileGetResp serviceProfileGetRespOld  = serviceProfileServiceLoraWanHttp.get(serviceProfileGetReq,domainOld,account,password);
//        ServiceProfileGetResp serviceProfileGetRespNew  = serviceProfileServiceLoraWanHttp.get(serviceProfileGetReq,domainNew,account,password);
//        OrganizationGetReq organizationGetReq = new OrganizationGetReq();
//        organizationGetReq.setOffset("0");
//        organizationGetReq.setLimit("10");
//        OrganizationGetResp organizationGetResp = organizationService.get(organizationGetReq,domainOld,account,password);
//        for (OrganizationGetResult organizationGetResult : organizationGetResp.getResult()) {
//            if (!"loraserver".equals(organizationGetResult.getName())){
//                continue;
//            }
//            ApplicationsGetReq applicationsGetReq = new ApplicationsGetReq();
//            applicationsGetReq.setLimit("100");
//            applicationsGetReq.setOffset("0");
//            applicationsGetReq.setOrganizationID(organizationGetResult.getId());
//            ApplicationsGetResp applicationsGetResp = applicationService.get(applicationsGetReq,domainOld,account,password);
//            for (ApplicationsGetResult applicationsGetResult : applicationsGetResp.getResult().stream().sorted(Comparator.comparing(ApplicationsGetResult::getId)).collect(Collectors.toList())) {
//                String id = applicationsGetResult.getId();
//                ApplicationsGetInfoResp applicationsGetInfoResp = applicationService.get(id,domainOld,account,password);
//                String name = applicationsGetResult.getName();
//                String description = applicationsGetResult.getDescription();
//                String organizationID = applicationsGetResult.getOrganizationID();
//                String serviceProfileID = applicationsGetResult.getServiceProfileID();
//                ApplicationsPostReq applicationsPostReq = new ApplicationsPostReq();
//                Application application = applicationsGetInfoResp.getApplication();
////                application.setId(id);
////                application.setName(name);
////                application.setDescription(description);
////                application.setOrganizationID(organizationID);
////                application.setServiceProfileID(serviceProfileID);
//                String serviceProfileIDNew;
//                Optional<ServiceProfileGetResult> optionalSPOld = serviceProfileGetRespOld.getResult().stream().filter(serviceProfileGetResult -> serviceProfileGetResult.getId().equals(serviceProfileID)).findFirst();
//                if (optionalSPOld.isPresent()){
//                    String spNameOld = optionalSPOld.get().getName();
//                    Optional<ServiceProfileGetResult> optionalSPNew = serviceProfileGetRespNew.getResult().stream().filter(serviceProfileGetResult -> serviceProfileGetResult.getName().equals(spNameOld)).findFirst();
//                    if (optionalSPNew.isPresent()){
//                        serviceProfileIDNew = optionalSPNew.get().getId();
//                    }else {
//                        continue;
//                    }
//                }else {
//                    continue;
//                }
//                application.setServiceProfileID(serviceProfileIDNew);
//                application.setOrganizationID("1");
//                applicationsPostReq.setApplication(application);
//                applicationService.post(applicationsPostReq,domainNew,account,password);
//            }
//        }
//    }
//
//    public void transferDeviceProfile(){
//        NetworkServerGetReq networkServerGetReq = new NetworkServerGetReq();
//        networkServerGetReq.setLimit("10");
//        networkServerGetReq.setOffset("0");
//        NetworkServerGetResp networkServerGetRespOld = networkServerServiceLoraWanHttp.get(networkServerGetReq,domainOld,account,password);
//        NetworkServerGetResp networkServerGetRespNew = networkServerServiceLoraWanHttp.get(networkServerGetReq,domainNew,account,password);
//        DeviceProfileGetReq deviceProfileGetReq = new DeviceProfileGetReq();
//        deviceProfileGetReq.setLimit("10");
//        deviceProfileGetReq.setOffset("0");
//        OrganizationGetReq organizationGetReq = new OrganizationGetReq();
//        organizationGetReq.setOffset("0");
//        organizationGetReq.setLimit("10");
//        OrganizationGetResp organizationGetResp = organizationService.get(organizationGetReq,domainOld,account,password);
//        for (OrganizationGetResult organizationGetResult : organizationGetResp.getResult()) {
//            if (!"loraserver".equals(organizationGetResult.getName())){
//                continue;
//            }
//            String organizationId = organizationGetResult.getId();
//                deviceProfileGetReq.setOrganizationID(organizationId);
//                DeviceProfileGetResp deviceProfileGetResp = deviceProfileServiceLoraWanHttp.get(deviceProfileGetReq,domainOld,account,password);
//                for (DeviceProfileGetResult deviceProfileGetResult : deviceProfileGetResp.getResult()) {
//                    String id = deviceProfileGetResult.getId();
//                    DeviceProfileGetInfoResp deviceProfileGetInfoResp = deviceProfileServiceLoraWanHttp.get(id,domainOld,account,password);
//                    DeviceProfile deviceProfile = deviceProfileGetInfoResp.getDeviceProfile();
//                    String networkServerIDNew;
//                    Optional<NetworkServerGetResult> optionalNetOld = networkServerGetRespOld.getResult().stream().filter(networkServerGetResult -> networkServerGetResult.getId().equals(deviceProfileGetResult.getNetworkServerID())).findFirst();
//                    if (optionalNetOld.isPresent()){
//                        String netNameOld = optionalNetOld.get().getName();
//                        Optional<NetworkServerGetResult> optionalNetNew = networkServerGetRespNew.getResult().stream().filter(networkServerGetResult -> networkServerGetResult.getName().equals(netNameOld)).findFirst();
//                        if (optionalNetNew.isPresent()){
//                            networkServerIDNew = optionalNetNew.get().getId();
//                        }else {
//                            continue;
//                        }
//                    }else {
//                        continue;
//                    }
//                    deviceProfile.setNetworkServerID(networkServerIDNew);
//                    deviceProfile.setOrganizationID("1");
//                    DeviceProfilePostReq deviceProfilePostReq = new DeviceProfilePostReq();
//                    deviceProfilePostReq.setDeviceProfile(deviceProfile);
//                    deviceProfileServiceLoraWanHttp.post(deviceProfilePostReq,domainNew,account,password);
//                }
//        }
//    }
//
//    public void transferDevice(){
//        int count = 0;
//        DeviceGetReq deviceGetReq = new DeviceGetReq();
//        deviceGetReq.setLimit("10000");
//        deviceGetReq.setOffset("0");
//        OrganizationGetReq organizationGetReq = new OrganizationGetReq();
//        organizationGetReq.setOffset("0");
//        organizationGetReq.setLimit("10");
//        OrganizationGetResp organizationGetResp = organizationService.get(organizationGetReq,domainOld,account,password);
//        for (OrganizationGetResult organizationGetResult : organizationGetResp.getResult()) {
//            if (!"loraserver".equals(organizationGetResult.getName())){
//                continue;
//            }
//            DeviceProfileGetReq deviceProfileGetReq = new DeviceProfileGetReq();
//            deviceProfileGetReq.setLimit("10");
//            deviceProfileGetReq.setOffset("0");
//            deviceProfileGetReq.setOrganizationID(organizationGetResult.getId());
//            DeviceProfileGetResp deviceProfileGetRespOld = deviceProfileServiceLoraWanHttp.get(deviceProfileGetReq,domainOld,account,password);
//            DeviceProfileGetResp deviceProfileGetRespNew = deviceProfileServiceLoraWanHttp.get(deviceProfileGetReq,domainNew,account,password);
//            ApplicationsGetReq applicationsGetReq = new ApplicationsGetReq();
//            applicationsGetReq.setLimit("100");
//            applicationsGetReq.setOffset("0");
//            applicationsGetReq.setOrganizationID(organizationGetResult.getId());
//            ApplicationsGetResp applicationsGetResp = applicationService.get(applicationsGetReq,domainOld,account,password);
//            ApplicationsGetResp applicationsGetRespNew = applicationService.get(applicationsGetReq,domainNew,account,password);
//            for (ApplicationsGetResult applicationsGetResult : applicationsGetResp.getResult()) {
//                String applicationId = applicationsGetResult.getId();
//                String applicationName = applicationsGetResult.getName();
//                deviceGetReq.setApplicationID(applicationId);
//                DeviceGetResp deviceGetResp = deviceServiceLoraWanHttp.get(deviceGetReq,domainOld,account,password);
//                for (DeviceGetResult deviceGetResult : deviceGetResp.getResult()) {
//                    try {
//                        Thread.sleep(500);
//                    if (count%100==0){
//                        Thread.sleep(1000);
//                        log.info("thread sleep...");
//                    }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    String devEUI = deviceGetResult.getDevEUI();
//                    DeviceGetInfoResp deviceGetInfoResp = deviceServiceLoraWanHttp.get(devEUI,domainOld,account,password);
//                    Device device = deviceGetInfoResp.getDevice();
//                    String applicationIdNew;
//                    String deviceProfileIdNew;
//                    Optional<ApplicationsGetResult> optionalAppNew = applicationsGetRespNew.getResult().stream().filter(applicationsGetResultNew -> applicationsGetResultNew.getName().equals(applicationName)).findFirst();
//                    if (optionalAppNew.isPresent()){
//                        applicationIdNew = optionalAppNew.get().getId();
//                        Optional<DeviceProfileGetResult> optionalDevOld = deviceProfileGetRespOld.getResult().stream().filter(deviceProfileGetResult -> deviceProfileGetResult.getId().equals(deviceGetResult.getDeviceProfileID())).findFirst();
//                        if (optionalDevOld.isPresent()){
//                            String deviceProfileNameOld = optionalDevOld.get().getName();
//                            Optional<DeviceProfileGetResult> optionalDevNew = deviceProfileGetRespNew.getResult().stream().filter(deviceProfileGetResult -> deviceProfileGetResult.getName().equals(deviceProfileNameOld)).findFirst();
//                            if (optionalDevNew.isPresent()){
//                                deviceProfileIdNew = optionalDevNew.get().getId();
//                            }else {
//                                continue;
//                            }
//                        }else {
//                            continue;
//                        }
//                    }else {
//                        continue;
//                    }
//                    device.setApplicationID(applicationIdNew);
//                    device.setDeviceProfileID(deviceProfileIdNew);
//                    DevicePostReq devicePostReq = new DevicePostReq();
//                    devicePostReq.setDevice(device);
//                    boolean insert = deviceServiceLoraWanHttp.post(devicePostReq,domainNew,account,password,true);
//                    String isSuccess = "fail";
//                    if (insert){
//                        isSuccess = "Success";
//                    }
//                    log.info("insert device("+devEUI+") "+ isSuccess);
//                    count++;
//                }
//            }
//        }
//    }
//
//    public void transferDeviceKey(){
//        DeviceGetReq deviceGetReq = new DeviceGetReq();
//        deviceGetReq.setLimit("10000");
//        deviceGetReq.setOffset("0");
//        OrganizationGetReq organizationGetReq = new OrganizationGetReq();
//        organizationGetReq.setOffset("0");
//        organizationGetReq.setLimit("10");
//        OrganizationGetResp organizationGetResp = organizationService.get(organizationGetReq,domainOld,account,password);
//        for (OrganizationGetResult organizationGetResult : organizationGetResp.getResult()) {
//            if (!"loraserver".equals(organizationGetResult.getName())){
//                continue;
//            }
//            ApplicationsGetReq applicationsGetReq = new ApplicationsGetReq();
//            applicationsGetReq.setLimit("100");
//            applicationsGetReq.setOffset("0");
//            applicationsGetReq.setOrganizationID(organizationGetResult.getId());
//            ApplicationsGetResp applicationsGetResp = applicationService.get(applicationsGetReq, domainOld, account, password);
//            for (ApplicationsGetResult applicationsGetResult : applicationsGetResp.getResult()) {
//                String applicationId = applicationsGetResult.getId();
//                deviceGetReq.setApplicationID(applicationId);
//                DeviceGetResp deviceGetResp = deviceServiceLoraWanHttp.get(deviceGetReq, domainOld, account, password);
//                for (DeviceGetResult deviceGetResult : deviceGetResp.getResult()) {
//                    try {
//                        Thread.sleep(250);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    String devEUI = deviceGetResult.getDevEUI();
//                    DeviceKeysGetResp deviceKeysGetResp = deviceServiceLoraWanHttp.getDeviceKey(devEUI,domainOld,account,password);
//                    DeviceKeysPostReq deviceKeysPostReq = new DeviceKeysPostReq();
//                    deviceKeysPostReq.setDeviceKeys(deviceKeysGetResp.getDeviceKeys());
//                    boolean insert = deviceServiceLoraWanHttp.postDeviceKey(domainNew,account,password,devEUI,deviceKeysPostReq,true);
//                    String isSuccess = "fail";
//                    if (insert){
//                        isSuccess = "Success";
//                    }
//                    log.info("insert device("+devEUI+") "+ isSuccess);
//                }
//            }
//        }
//    }
//
//
//
//}
