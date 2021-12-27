package com.yunhorn.core.chirpstack.sync;

import com.yunhorn.core.chirpstack.client.api.ApplicationServiceLoraWanHttp;
import com.yunhorn.core.chirpstack.client.api.OrganizationServiceLoraWanHttp;
import com.yunhorn.core.chirpstack.client.api.ServiceProfileServiceLoraWanHttp;
import com.yunhorn.core.chirpstack.client.request.application.Application;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsGetReq;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsPostReq;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsPutReq;
import com.yunhorn.core.chirpstack.client.request.organization.OrganizationGetReq;
import com.yunhorn.core.chirpstack.client.request.serviceprofile.ServiceProfileGetReq;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsGetInfoResp;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsGetResp;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsGetResult;
import com.yunhorn.core.chirpstack.client.response.organization.OrganizationGetResp;
import com.yunhorn.core.chirpstack.client.response.organization.OrganizationGetResult;
import com.yunhorn.core.chirpstack.client.response.serviceprofile.ServiceProfileGetResp;
import com.yunhorn.core.chirpstack.client.response.serviceprofile.ServiceProfileGetResult;
import com.yunhorn.core.chirpstack.dto.SyncReq;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
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

    public void syncApplication(SyncReq syncReq){
        String sourceDomain = syncReq.getSourceDomain();
        String sourceAccount = syncReq.getSourceAccount();
        String sourcePassword = syncReq.getSourcePassword();
        String targetDomain = syncReq.getTargetDomain();
        String targetAccount = syncReq.getTargetAccount();
        String targetPassword = syncReq.getTargetPassword();
        String sourceOrganizationName = syncReq.getSourceOrganizationName();
        String targetOrganizationName = syncReq.getTargetOrganizationName();
        String targetServiceProfileName = syncReq.getTargetServiceProfileName();

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
        for (ApplicationsGetResult sourceApplicationsGetResult : sourceApplicationsResp.getResult()) {
            ApplicationsGetResult targetApplicationGetResult = targetApplicationMap.get(sourceApplicationsGetResult.getName());
            ApplicationsGetInfoResp sourceApplicationInfo = applicationServiceLoraWanHttp.get(sourceApplicationsGetResult.getId(),sourceDomain,sourceAccount,sourcePassword);
            if (targetApplicationGetResult!=null){
                ApplicationsGetInfoResp targetApplicationInfo = applicationServiceLoraWanHttp.get(targetApplicationGetResult.getId(),targetDomain,targetAccount,targetPassword);
                Application sourceApplication = sourceApplicationInfo.getApplication();
                Application targetApplicationOriginal = targetApplicationInfo.getApplication();
                if (!sourceApplication.equals(targetApplicationOriginal)){
                    Application targetApplication = sourceApplication.copyProperties(targetOrganizationID,targetServiceProfileId,true);
                    applicationServiceLoraWanHttp.put(new ApplicationsPutReq(targetApplication),targetDomain,targetAccount,targetPassword);
                    log.info("Update target chirpStack Application|Before update:{}|After update:{}", JSONUtils.beanToJson(targetApplicationOriginal),JSONUtils.beanToJson(targetApplication));
                }
            }else {
                ApplicationsPostReq applicationsPostReq = new ApplicationsPostReq();
                applicationsPostReq.setApplication(sourceApplicationInfo.getApplication().copyProperties(targetOrganizationID,targetServiceProfileId,false));
                applicationServiceLoraWanHttp.post(applicationsPostReq,targetDomain,targetAccount,targetPassword);
                log.info("Insert Application to target chirpStack|{}",JSONUtils.beanToJson(applicationsPostReq.getApplication()));
            }
        }

    }
}
