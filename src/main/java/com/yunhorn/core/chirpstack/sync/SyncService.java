package com.yunhorn.core.chirpstack.sync;

import com.yunhorn.core.chirpstack.client.api.ApplicationServiceLoraWanHttp;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsGetReq;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsGetResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ljm
 * @date 2021/12/23 11:25
 */
@Service
public class SyncService {

    @Autowired
    private ApplicationServiceLoraWanHttp applicationServiceLoraWanHttp;

    public void syncApplication(String sourceDomain,String sourceAccount,String sourcePassword,String targetDomain,String targetAccount,String targetPassword){

        applicationServiceLoraWanHttp.get(new ApplicationsGetReq(),sourceDomain,sourceAccount,sourcePassword);//查询源chirpStack的application数据

        applicationServiceLoraWanHttp.get(new ApplicationsGetReq(),targetDomain,targetAccount,targetPassword);//查询需要同步的目标chirpStack的application数据

    }
}
