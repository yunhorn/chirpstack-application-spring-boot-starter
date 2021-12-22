package io.github.yunhorn.chirpstackappsyncer.dto;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/12/22 14:57
 */
@Data
public class CurrentOperator {
    private String account;
    private String password;
    private String currentUser;
    public CurrentOperator(){

    }

    public CurrentOperator(String account,String password,String currentUser){
        this.account = account;
        this.password = password;
        this.currentUser = currentUser;
    }
}
