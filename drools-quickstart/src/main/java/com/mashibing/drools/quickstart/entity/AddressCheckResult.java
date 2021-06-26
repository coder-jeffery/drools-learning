package com.mashibing.drools.quickstart.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sunzhiqiang23
 * @date 2019/9/1011:26
 */
@Data
@Accessors(chain = true)
public class AddressCheckResult {

    /**
     * true:通过校验；false：未通过校验
     */
    private boolean postCodeResult = false;
}
