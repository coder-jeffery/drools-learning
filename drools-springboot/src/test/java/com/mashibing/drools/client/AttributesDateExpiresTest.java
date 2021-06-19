package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.AttributesDateEffectiveEntity;
import com.mashibing.drools.entity.AttributesDateExpiresEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class AttributesDateExpiresTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        AttributesDateExpiresEntity attributesDateExpiresEntity = new AttributesDateExpiresEntity();
        attributesDateExpiresEntity.setNum(20);

        kieSession.insert(attributesDateExpiresEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
