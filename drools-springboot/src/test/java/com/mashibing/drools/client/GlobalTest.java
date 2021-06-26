package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.AttributesActivationGroupEntity;
import com.mashibing.drools.entity.GlobalEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class GlobalTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        GlobalEntity globalEntity = new GlobalEntity();
        globalEntity.setNum(20);

        ArrayList<Object> globalList = new ArrayList<>();

        Integer globalCount = 10;
        kieSession.setGlobal("globalCount", 10);
        kieSession.setGlobal("globalList", globalList);

        kieSession.insert(globalEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("globalCount=" + globalCount);
        System.out.println("globalList=" + globalList);
    }
}
