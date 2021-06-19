package com.mashibing.drools.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author sunzhiqiang23
 * @date 2021-06-16 21:11
 */

@Data
@Accessors(chain = true)
public class ComparisonEntity {

    /**
     *名字集合
     */
    private String names;

    /**
     * 字符串集合
     */
    private List<String> list;

}
