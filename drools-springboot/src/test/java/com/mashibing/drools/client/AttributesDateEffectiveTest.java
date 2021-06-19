package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.AttributesActivationGroupEntity;
import com.mashibing.drools.entity.AttributesDateEffectiveEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class AttributesDateEffectiveTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        AttributesDateEffectiveEntity attributesDateEffectiveEntity = new AttributesDateEffectiveEntity();
        attributesDateEffectiveEntity.setNum(20);

        kieSession.insert(attributesDateEffectiveEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
