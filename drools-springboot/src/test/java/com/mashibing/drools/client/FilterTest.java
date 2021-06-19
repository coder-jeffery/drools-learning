package com.mashibing.drools.client;

import com.mashibing.drools.DroolsApplicationTests;
import com.mashibing.drools.entity.ComparisonEntity;
import com.mashibing.drools.entity.FilterEntity;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
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
public class FilterTest extends DroolsApplicationTests {

    @Resource
    public KieBase kieBase;

    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        FilterEntity filterEntity = new FilterEntity();
        filterEntity.setNum(30);

        kieSession.insert(filterEntity);

        //通过规则过滤器实现只执行指定规则
        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_filter_1"));
        kieSession.dispose();
    }
}
