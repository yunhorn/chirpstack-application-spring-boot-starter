package com.yunhorn.core.chirpstack.client.api;

import com.google.common.collect.Maps;
import com.yunhorn.core.chirpstack.dto.CurrentOperator;
import com.yunhorn.core.chirpstack.helper.GlobalHelper;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import com.yunhorn.core.chirpstack.config.UserInfo;
import com.yunhorn.core.chirpstack.util.RestTemplateUtil;
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

    protected <T> T sendHttpsGet(String domain,String path,String account,String password, Map<String,String> headerMap,Map<String, String> params,Class<T> responseType){
        return request(domain,path,account,password,headerMap,params,responseType,HttpMethod.GET);
    }

    private <T> T request(String domain, String path,String account,String password, Map<String,String> headerMap, Object reqBody,Class<T> responseType,HttpMethod httpMethod){
        if (headerMap==null){
            headerMap = Maps.newHashMap();
        }
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            //用户名或者密码为空时 则默认根据domain从配置文件里找到对应的账号密码
            CurrentOperator currentOperator = getCurrentOperator(domain);
            if (currentOperator==null){
                return null;
            }
            account = currentOperator.getAccount();
            password = currentOperator.getPassword();
        }
        if (domain.endsWith("/")){
            //若传进来的domain以“/”结尾 则需去掉
            domain = domain.substring(0,domain.length()-1);
        }
        checkHeaderMap(domain, account,password, headerMap);
        String result = request(domain, path, headerMap, reqBody, httpMethod);
        if (checkIsAuthenticationFailed(result)){
            result = retryRequest(domain,path,account,password,headerMap,reqBody,httpMethod);
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

    private String retryRequest(String domain, String path, String account,String password, Map<String,String> headerMap, Object reqBody, HttpMethod httpMethod){
        Map<String,String> authHeadMap = getAuthHeadMap(domain,account,password);
        if (headerMap==null){
            headerMap = Maps.newHashMap();
        }
        headerMap.putAll(authHeadMap);
        return request(domain, path, headerMap, reqBody, httpMethod);
    }

    private void checkHeaderMap(String domain,String account,String password,Map<String,String> headerMap){
        if (!headerMap.containsKey("Grpc-Metadata-Authorization") && StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)){
            String cacheKey = GlobalHelper.getCacheKey(GlobalHelper.CHIRPSTACK_USER_INFO_CACHE
                    ,domain,account);
            String token = tokenMap.get(cacheKey);
            if (StringUtils.isBlank(token)){
                Map<String,String> authHeadMap = getAuthHeadMap(domain,account,password);
                headerMap.putAll(authHeadMap);
            }else {
                headerMap.put("Grpc-Metadata-Authorization",token);
            }
        }
    }

    private CurrentOperator getCurrentOperator(String domain){
        if (userInfo.getSourceDomain().equals(domain)){
            return new CurrentOperator(userInfo.getSourceAccount(),userInfo.getSourcePassword());
        }else if (userInfo.getTargetDomain().equals(domain)){
            return new CurrentOperator(userInfo.getTargetAccount(),userInfo.getTargetPassword());
        }
        return null;
    }

    protected Map<String,String> getAuthHeadMap(String domain,String account,String password){
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Collections.emptyMap();
        }
        String token = login(domain, account, password);
        Map<String,String> headerMap = Collections.singletonMap("Grpc-Metadata-Authorization","Bearer "+ token);
        String cacheKey = GlobalHelper.getCacheKey(GlobalHelper.CHIRPSTACK_USER_INFO_CACHE
                ,domain,account);
        tokenMap.put(cacheKey,"Bearer "+ token);
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

    protected <T> T sendHttpsPost(String domain, String path,String account,String password, Map<String,String> headerMap, Object reqBody,Class<T> responseType){
        return request(domain,path,account,password,headerMap,reqBody,responseType,HttpMethod.POST);
    }

    protected <T> T sendHttpsPut(String domain, String path,String account,String password, Map<String,String> headerMap, Object reqBody,Class<T> responseType){
        return request(domain,path,account,password,headerMap,reqBody,responseType,HttpMethod.PUT);
    }

    protected <T> T sendHttpsDelete(String domain, String path,String account,String password, Map<String,String> headerMap, Object reqBody,Class<T> responseType){
        return request(domain,path,account,password,headerMap,reqBody,responseType,HttpMethod.DELETE);
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
