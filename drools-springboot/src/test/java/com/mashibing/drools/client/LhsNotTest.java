package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.LhsExistsEntity;
import com.mashibing.drools.entity.LhsNotEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class LhsNotTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        LhsNotEntity lhsNotEntity = new LhsNotEntity();
        lhsNotEntity.setAge(1);

        kieSession.insert(lhsNotEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
