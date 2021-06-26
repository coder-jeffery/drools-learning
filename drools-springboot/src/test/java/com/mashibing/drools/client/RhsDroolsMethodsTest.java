package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.RhsDroolsMethodsEntity;
import com.mashibing.drools.entity.RhsHaftEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class RhsDroolsMethodsTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        RhsDroolsMethodsEntity rhsDroolsMethodsEntity = new RhsDroolsMethodsEntity();
        rhsDroolsMethodsEntity.setAge(5);

        kieSession.insert(rhsDroolsMethodsEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
