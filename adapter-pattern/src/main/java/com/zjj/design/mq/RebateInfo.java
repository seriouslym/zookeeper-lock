package com.zjj.design.mq;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 统一消息体
 */
@Data
public class RebateInfo {
    public String userId;  // 用户ID
    public String bizId;   // 业务ID
    public Long bizTime;   // 业务时间
    public String desc;    // 业务描述


}
