package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.ComparisonEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class ComparisonTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();

        ComparisonEntity comparisonEntity = new ComparisonEntity();
        comparisonEntity.setNames("张三");
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        comparisonEntity.setList(list);

        kieSession.insert(comparisonEntity);

        int num = kieSession.fireAllRules();
        System.out.println("匹配到的规则数："+num);
        kieSession.dispose();
    }
}
