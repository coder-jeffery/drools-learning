package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.LhsEvalEntity;
import com.mashibing.drools.entity.LhsInEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class LhsEvalTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        LhsEvalEntity lhsEvalEntity = new LhsEvalEntity();
        lhsEvalEntity.setAge(20);

        kieSession.insert(lhsEvalEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
