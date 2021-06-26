package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.GlobalEntity;
import com.mashibing.drools.entity.QueryEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class QueryTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();

        QueryEntity queryEntity1= new QueryEntity();
        QueryEntity queryEntity2= new QueryEntity();
        QueryEntity queryEntity3= new QueryEntity();

        queryEntity1.setName("张三").setAge(10);
        queryEntity2.setName("李四").setAge(20);
        queryEntity3.setName("王五").setAge(30);


        kieSession.insert(queryEntity1);
        kieSession.insert(queryEntity2);
        kieSession.insert(queryEntity3);

        QueryResults results1 = kieSession.getQueryResults("query_1");
        QueryResults results2 = kieSession.getQueryResults("query_2", 1, "张三");


        for (QueryResultsRow queryResultsRow : results1) {
            QueryEntity queryEntity = (QueryEntity) (queryResultsRow.get("$queryEntity"));
            System.out.println("query_1" + queryEntity);
        }

        for (QueryResultsRow queryResultsRow : results2) {
            QueryEntity queryEntity = (QueryEntity) (queryResultsRow.get("$queryEntity"));
            System.out.println("query_2" + queryEntity);
        }


        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
