package com.example.myanimationset.Bean;

import java.io.Serializable;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/23
 * desc:
 */
public class UserInfo implements Serializable {

    private String account;
    private String password;
    private int type;  //布局类型

    public UserInfo(){

    }

    public UserInfo(String account,String password){
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "account='" + account + "\'" + ", password='" + password + "'}";
    }
}
