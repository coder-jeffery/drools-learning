package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.AttributesAgendaGroupEntity;
import com.mashibing.drools.entity.AttributesAutoFocusEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class AttributesAutoFocusTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        AttributesAutoFocusEntity attributesAutoFocusEntity = new AttributesAutoFocusEntity();
        attributesAutoFocusEntity.setNum(20);

        kieSession.insert(attributesAutoFocusEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
