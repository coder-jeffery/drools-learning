package com.mashibing.drools.client;

import com.mashibing.pro1.Order;
import org.drools.core.io.impl.UrlResource;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author sunzhiqiang23
 * @date 2021-06-28 15:31
 */
public class WorkBenchTest {

    @Test
    public void test() throws Exception{
        /**
         * 通过此URL可以访问到maven仓库中的jar包
         * URL地址构成：http://ip地址:Tomcat端口号/WorkBench工程名/maven2/坐标/版本号/xxx.jar
         */
        String url = "http://localhost:8080/kie-web/maven2/com/mashibing/pro1/1.0.1/pro1-1.0.1.jar";

        KieServices kieServices = KieServices.Factory.get();
        UrlResource resource = (UrlResource) kieServices.getResources().newUrlResource(url);
        //认证
        resource.setUsername("kie-web");
        resource.setPassword("kie-web123");
        resource.setBasicAuthentication("enabled");

        KieRepository repository = kieServices.getRepository();

        KieModule kieModule = repository.addKieModule(kieServices.getResources().newInputStreamResource(resource.getInputStream()));

        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        KieSession session = kieContainer.newKieSession();

        Order order = new Order();
        order.setName("张三");
        order.setAge(30);
        session.insert(order);

        session.fireAllRules();
        session.dispose();
    }


}
