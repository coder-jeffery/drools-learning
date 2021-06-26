package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.LhsExistsEntity;
import com.mashibing.drools.entity.LhsInEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class LhsExistsTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        LhsExistsEntity lhsExistsEntity = new LhsExistsEntity();
        lhsExistsEntity.setAge(30);

        LhsExistsEntity lhsExistsEntity2 = new LhsExistsEntity();
        lhsExistsEntity2.setAge(30);

        kieSession.insert(lhsExistsEntity);
        kieSession.insert(lhsExistsEntity2);

        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
