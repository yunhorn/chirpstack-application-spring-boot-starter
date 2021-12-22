package io.github.yunhorn.chirpstackappsyncer.client.api;

import com.google.common.collect.Maps;
import io.github.yunhorn.chirpstackappsyncer.config.UserInfo;
import io.github.yunhorn.chirpstackappsyncer.dto.CurrentOperator;
import io.github.yunhorn.chirpstackappsyncer.helper.GlobalHelper;
import io.github.yunhorn.chirpstackappsyncer.util.JSONUtils;
import io.github.yunhorn.chirpstackappsyncer.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

/**
 * @author ljm
 * @date 2021/2/24 14:32
 */
@Service()
@Slf4j
public class BaseServiceLoraWanHttp {

    @Autowired
    private UserInfo userInfo;

    private Map<String,String> tokenMap = Maps.newHashMap();

    protected <T> T sendHttpsGet(String domain,String path, Map<String,String> headerMap,Map<String, String> params,Class<T> responseType){
        return request(domain,path,headerMap,params,responseType,HttpMethod.GET);
    }

    private <T> T request(String domain, String path, Map<String,String> headerMap, Object reqBody,Class<T> responseType,HttpMethod httpMethod){
        checkHeaderMap(domain, headerMap);
        String result = request(domain, path, headerMap, reqBody, httpMethod);
        if (checkIsAuthenticationFailed(result)){
            result = retryRequest(domain,path,headerMap,reqBody,httpMethod);
        }
        return (T) JSONUtils.jsonToBean(result,responseType);
    }

    private String request(String domain, String path, Map<String,String> headerMap, Object reqBody, HttpMethod httpMethod){
        if (HttpMethod.GET.equals(httpMethod)){
            return RestTemplateUtil.getInstance().get(domain+path,reqBody,headerMap,String.class);
        }else if (HttpMethod.POST.equals(httpMethod)){
            return RestTemplateUtil.getInstance().post(domain+path,reqBody,headerMap,String.class);
        }else if (HttpMethod.PUT.equals(httpMethod)){
            return RestTemplateUtil.getInstance().put(domain+path,reqBody,headerMap,String.class);
        }else if (HttpMethod.DELETE.equals(httpMethod)){
            return RestTemplateUtil.getInstance().delete(domain+path,reqBody,headerMap,String.class);
        }
        return null;
    }

    private boolean checkIsAuthenticationFailed(String result){
        Map<String,Object> resultMap = JSONUtils.jsonToMap(result);
        return resultMap!=null && resultMap.containsKey("error") && resultMap.get("error").toString().contains("authentication failed");
    }

    private String retryRequest(String domain, String path, Map<String,String> headerMap, Object reqBody, HttpMethod httpMethod){
        CurrentOperator currentOperator = getCurrentOperator(domain);
        Map<String,String> authHeadMap = getAuthHeadMap(domain,currentOperator.getAccount(),currentOperator.getPassword());
        if (headerMap==null){
            headerMap = Maps.newHashMap();
        }
        headerMap.putAll(authHeadMap);
        return request(domain, path, headerMap, reqBody, httpMethod);
    }

    private void checkHeaderMap(String domain,Map<String,String> headerMap){
        if (headerMap==null){
            headerMap = Maps.newHashMap();
        }
        if (!headerMap.containsKey("Grpc-Metadata-Authorization")){
            CurrentOperator currentOperator = getCurrentOperator(domain);
            String cacheKey = GlobalHelper.getCacheKey(GlobalHelper.CHIRPSTACK_USER_INFO_CACHE
                    ,currentOperator.getCurrentUser(),currentOperator.getAccount());
            String token = tokenMap.get(cacheKey);
            if (StringUtils.isBlank(token)){
                Map<String,String> authHeadMap = getAuthHeadMap(domain,currentOperator.getAccount(),currentOperator.getPassword());
                headerMap.putAll(authHeadMap);
            }
        }
    }

    private CurrentOperator getCurrentOperator(String domain){
        if (userInfo.getSourceDomain().equals(domain)){
            return new CurrentOperator(userInfo.getSourceAccount(),userInfo.getSourcePassword(),GlobalHelper.CHIRPSTACK_SOURCE);
        }else{
            return new CurrentOperator(userInfo.getTargetAccount(),userInfo.getTargetPassword(),GlobalHelper.CHIRPSTACK_TARGET);
        }
    }

    protected Map<String,String> getAuthHeadMap(String domain,String account,String password){
        String token = login(domain, account, password);
        Map<String,String> headerMap = Collections.singletonMap("Grpc-Metadata-Authorization","Bearer "+ token);
        CurrentOperator currentOperator = getCurrentOperator(domain);
        String cacheKey = GlobalHelper.getCacheKey(GlobalHelper.CHIRPSTACK_USER_INFO_CACHE
                ,currentOperator.getCurrentUser(),account);
        tokenMap.put(cacheKey,token);
        return headerMap;
    }

//    protected String login(String domain,String account,String password,boolean getNewToken){
//        String token = null;
//        if (!getNewToken){
//            token = LoraWanServerHttpRCache.getToken(domain,account);
//        }
//
//        return Optional.ofNullable(token).orElseGet(()-> {
//            String resp = login(domain, account, password);
//            if (resp!=null){
//                LoraWanServerHttpRCache.setToken(domain,account, resp, LoraWanServerHttpRCache.getExpire());
//                return resp;
//            }
//            return null;
//        });
//    }

    protected <T> T sendHttpsPost(String domain, String path, Map<String,String> headerMap, Object reqBody,Class<T> responseType){
        return request(domain,path,headerMap,reqBody,responseType,HttpMethod.POST);
    }

    protected <T> T sendHttpsPut(String domain, String path, Map<String,String> headerMap, Object reqBody,Class<T> responseType){
        return request(domain,path,headerMap,reqBody,responseType,HttpMethod.PUT);
    }

    protected <T> T sendHttpsDelete(String domain, String path, Map<String,String> headerMap, Object reqBody,Class<T> responseType){
        return request(domain,path,headerMap,reqBody,responseType,HttpMethod.DELETE);
    }

//    protected <T> T sendHttpsPost(String domain, String path, Map<String,String> headerMap, Map<String, Map> reqMap,Class<T> responseType){
//
//    }

    /**
     * 登录loraWanServer获取token
     * @param domain 域名
     * @param account 账号
     * @param password 密码
     * @return
     */
    public String login(String domain,String account,String password){
        Map<String,String> loginMap = Maps.newHashMap();
        //最新的loraserver版本的API接口 username字段替换成了email
        loginMap.put("username", account);
        loginMap.put("email", account);
        loginMap.put("password",password);
        Map<String,Object> respMap = RestTemplateUtil.getInstance().post(domain+"/api/internal/login", loginMap,null,Map.class);
        if (respMap!=null && respMap.containsKey("jwt")){
            return (String) respMap.get("jwt");
        }
        return null;
    }
}
