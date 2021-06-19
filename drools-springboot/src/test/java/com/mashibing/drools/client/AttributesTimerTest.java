package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.AttributesAgendaGroupEntity;
import com.mashibing.drools.entity.AttributesTimerEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class AttributesTimerTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test() throws InterruptedException {

        KieSession kieSession = kieBase.newKieSession();
        AttributesTimerEntity attributesTimerEntity = new AttributesTimerEntity();
        attributesTimerEntity.setNum(20);

        kieSession.insert(attributesTimerEntity);
        kieSession.fireUntilHalt();

        Thread.sleep(10000);
        kieSession.halt();

        kieSession.dispose();
    }
}
