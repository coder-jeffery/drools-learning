package com.mashibing.drools.client;

import com.alibaba.fastjson.JSON;
import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.KieSessionEntity;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 无状态 session
 * @author sunzhiqiang23
 * @date 2021-06-30 14:44
 */
public class SessionLessTest extends DroolsApplicationTests {

    @Resource
    private KieBase kieBase;

    @Test
    public void testStatelessSession() {
        StatelessKieSession statelessKieSession = kieBase.newStatelessKieSession();
        List<Command> cmds = new ArrayList<>();
        KieSessionEntity kieSessionEntity = new KieSessionEntity();
        kieSessionEntity.setNum(10);
        kieSessionEntity.setValid(false);
        cmds.add(CommandFactory.newInsert(kieSessionEntity, "kieSessionEntity"));
        statelessKieSession.execute(CommandFactory.newBatchExecution(cmds));

        System.out.println(kieSessionEntity);
    }
}
