package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.RhsInsertEntity;
import com.mashibing.drools.entity.RhsUpdateEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class RhsInsertTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        RhsInsertEntity rhsInsertEntity = new RhsInsertEntity();
        rhsInsertEntity.setAge(5);

        kieSession.insert(rhsInsertEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
