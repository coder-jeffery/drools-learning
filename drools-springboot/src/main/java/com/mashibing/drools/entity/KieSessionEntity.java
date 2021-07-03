package com.mashibing.drools.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunzhiqiang23
 * @date 2021-06-18 1:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KieSessionEntity {

    private Integer num;

    private boolean valid;
}
