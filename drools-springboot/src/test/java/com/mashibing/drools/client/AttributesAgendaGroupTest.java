package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.AttributesActivationGroupEntity;
import com.mashibing.drools.entity.AttributesAgendaGroupEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class AttributesAgendaGroupTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        AttributesAgendaGroupEntity attributesAgendaGroupEntity = new AttributesAgendaGroupEntity();
        attributesAgendaGroupEntity.setNum(20);

        kieSession.insert(attributesAgendaGroupEntity);
        kieSession.getAgenda().getAgendaGroup("customAgendaGroup2").setFocus();

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
