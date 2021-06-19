package com.mashibing.drools.quickstart.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sunzhiqiang23
 * @date 2019/9/10 11:25
 */
@Data
@Accessors(chain = true)
public class Address {
    private String postcode;
    private String street;
}
