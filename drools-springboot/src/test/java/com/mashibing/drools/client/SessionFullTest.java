package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.Fire;
import com.mashibing.drools.entity.KieSessionEntity;
import com.mashibing.drools.entity.Room;
import com.mashibing.drools.entity.Sprinkler;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.command.Command;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.command.CommandFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 无状态 session
 * @author sunzhiqiang23
 * @date 2021-06-30 14:44
 */
public class SessionFullTest extends DroolsApplicationTests {

    @Resource
    private KieBase kieBase;

    @Test
    public void testStatelessSession() {
        KieSession ksession = kieBase.newKieSession();
        String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
        Map<String, Room> name2room = new HashMap<String,Room>();
        for( String name: names ){
            Room room = new Room( name );
            name2room.put( name, room );
            ksession.insert( room );
            Sprinkler sprinkler = new Sprinkler( room );
            ksession.insert( sprinkler );
        }

        ksession.fireAllRules();


        Fire kitchenFire = new Fire( name2room.get( "kitchen" ) );
        Fire officeFire = new Fire( name2room.get( "office" ) );

        FactHandle kitchenFireHandle = ksession.insert( kitchenFire );
        FactHandle officeFireHandle = ksession.insert( officeFire );

        ksession.fireAllRules();

        ksession.delete( kitchenFireHandle );
        ksession.delete( officeFireHandle );

        ksession.fireAllRules();

        ksession.dispose();

    }
}
