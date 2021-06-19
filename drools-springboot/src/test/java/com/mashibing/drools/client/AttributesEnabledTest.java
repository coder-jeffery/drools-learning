package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.AttributesEnabledEntity;
import com.mashibing.drools.entity.ComparisonEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class AttributesEnabledTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        AttributesEnabledEntity attributesEnabledEntity = new AttributesEnabledEntity();
        attributesEnabledEntity.setNum(20);

        kieSession.insert(attributesEnabledEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
