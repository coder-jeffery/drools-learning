package com.mashibing.drools.quickstart.client;

import com.mashibing.drools.quickstart.entity.Order;
import com.mashibing.drools.quickstart.entity.User;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.junit.jupiter.api.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 需求
 * 计算额外积分金额 规则如下: 订单原价金额
 * 100以下, 不加分
 * 100-500 加100分
 * 500-1000 加500分
 * 1000 以上 加1000分
 */
public class DroolsTests {

    public KieSession getKieSessionFromClassPathResource(){
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("rules/point-rules.drl"), ResourceType.DRL);
        //BUILD RULEBASE
        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addPackages( kbuilder.getKnowledgePackages() );
        //NEW WORKING MEMORY
        final KieSession session = kbase.newKieSession();
        return session;
    }

    public KieSession getKieSessionFromByte(){
        String rulsStr = "package rules\n" +
                "\n" +
                "import com.mashibing.drools.quickstart.entity.Order\n" +
                "import com.mashibing.drools.quickstart.entity.User\n" +
                "\n" +
                "rule \"zero\"\n" +
                "    no-loop true\n" +
                "    lock-on-active true\n" +
                "    salience 1\n" +
                "    when\n" +
                "        $order : Order(price <= 100)\n" +
                "    then\n" +
                "        $order.setScore(0);\n" +
                "        update($order);\n" +
                "end\n" +
                "\n" +
                "rule \"add100\"\n" +
                "    no-loop true\n" +
                "    lock-on-active true\n" +
                "    salience 1\n" +
                "    when\n" +
                "        $order : Order(price > 100 && price <= 500)\n" +
                "//        and $user : User(level>3)\n" +
                "    then\n" +
                "        System.out.println($order.getUser());\n" +
                "        $order.setScore(100);\n" +
                "        update($order);\n" +
                "end\n" +
                "\n" +
                "\n" +
                "rule \"add500\"\n" +
                "    no-loop true\n" +
                "    lock-on-active true\n" +
                "    salience 1\n" +
                "    when\n" +
                "        $order : Order(price > 500 && price <= 1000)\n" +
                "    then\n" +
                "        $order.setScore(500);\n" +
                "        update($order);\n" +
                "end\n" +
                "\n" +
                "rule \"add1000\"\n" +
                "    no-loop true\n" +
                "    lock-on-active true\n" +
                "    salience 1\n" +
                "    when\n" +
                "        $order : Order(price > 1000)\n" +
                "    then\n" +
                "        $order.setScore(1000);\n" +
                "        update($order);\n" +
                "end";
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("rules/point-rules.drl"), ResourceType.DRL);
        kbuilder.add(ResourceFactory.newByteArrayResource(rulsStr.getBytes()), ResourceType.DRL);
        //BUILD RULEBASE
        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addPackages( kbuilder.getKnowledgePackages() );
        //NEW WORKING MEMORY
        final KieSession session = kbase.newKieSession();
        return session;
    }

    @Test
    public void droolsOrderTest() throws Exception {
        KieSession kieSession = getKieSessionFromByte();
        List<Order> orderList = getInitData();
        for (Order order: orderList) {
            // 1-规则引擎处理逻辑
            kieSession.insert(order);
            kieSession.fireAllRules();
            // 2-执行完规则后, 执行相关的逻辑
            addScore(order);
        }
        kieSession.dispose();
    }

    private static void addScore(Order o){
        System.out.println("用户" + o.getUser().getName() + "享受额外增加积分: " + o.getScore());
    }

    private static List<Order> getInitData() throws Exception {
        List<Order> orderList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        {
            Order order = new Order();
            order.setPrice(80);
            order.setBookingDate(df.parse("2015-07-01"));
            User user = new User();
            user.setLevel(1);
            user.setName("Name1");
            order.setUser(user);
            order.setScore(111);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setPrice(200);
            order.setBookingDate(df.parse("2015-07-02"));
            User user = new User();
            user.setLevel(2);
            user.setName("Name2");
            order.setUser(user);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setPrice(800);
            order.setBookingDate(df.parse("2015-07-03"));
            User user = new User();
            user.setLevel(3);
            user.setName("Name3");
            order.setUser(user);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setPrice(1500);
            order.setBookingDate(df.parse("2015-07-04"));
            User user = new User();
            user.setLevel(4);
            user.setName("Name4");
            order.setUser(user);
            orderList.add(order);
        }
        return orderList;
    }







}
