package com.yunhorn.core.chirpstack.dto;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/12/22 14:57
 */
@Data
public class CurrentOperator {
    private String account;
    private String password;
    public CurrentOperator(){

    }
    public CurrentOperator(String account,String password){
        this.account = account;
        this.password = password;
    }
}
