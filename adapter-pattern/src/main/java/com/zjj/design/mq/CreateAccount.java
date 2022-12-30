package com.zjj.design.mq;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateAccount {
    private String number; // 开户账号
    private String address; // 开户地
    private Long accountDate; // 开户时间
    private String desc; // 开户描述

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
