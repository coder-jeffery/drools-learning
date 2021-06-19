package com.mashibing.drools.quickstart.entity;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sunzhiqiang23
 * @date 2019/9/10 11:58
 */
@Data
@Accessors(chain = true)
public class User {

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户级别
     */
    private int level;
}