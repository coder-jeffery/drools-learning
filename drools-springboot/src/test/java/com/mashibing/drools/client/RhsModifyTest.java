package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.RhsModifyEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class RhsModifyTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        RhsModifyEntity rhsModifyEntity = new RhsModifyEntity();
        rhsModifyEntity.setAge(5);

        kieSession.insert(rhsModifyEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
