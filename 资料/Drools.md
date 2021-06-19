规则引擎-drools

规则引擎

> ![1623149685836](E:\teaching\drools\课程大纲.png)

# 1 .场景

## 1.1需求

```
商城系统消费赠送积分

100元以下, 不加分 
100元-500元 加100分 
500元-1000元 加500分 
1000元 以上 加1000分
......

```

## 1.2传统做法

### 1.2.1 if...else

```
if (order.getAmout() <= 100){
    order.setScore(0);
    addScore(order);
}else if(order.getAmout() > 100 && order.getAmout() <= 500){
    order.setScore(100);
    addScore(order);
}else if(order.getAmout() > 500 && order.getAmout() <= 1000){
    order.setScore(500);
    addScore(order);
}else{
    order.setScore(1000);
    addScore(order);
}
```

### 1.2.2 策略

```
interface Strategy {
    addScore(int num1,int num2);
}

class Strategy1 {
    addScore(int num1);
}
......................
interface StrategyN {
    addScore(int num1);
}

class Environment {
    private Strategy strategy;

    public Environment(Strategy strategy) {
        this.strategy = strategy;
    }

    public int addScore(int num1) {
        return strategy.addScore(num1);
    }
}
```

### 1.2.3 问题？

```
以上解决方法问题思考：
如果需求变更，积分层次结构增加，积分比例调整？
数据库？

遇到的问题瓶颈：
第一，我们要简化if else结构,让业务逻辑和数据分离！
第二，分离出的业务逻辑必须要易于编写，至少单独编写这些业务逻辑，要比写代码快！
第三，分离出的业务逻辑必须要比原来的代码更容易读懂！
第四，分离出的业务逻辑必须比原来的易于维护，至少改动这些逻辑，应用程序不用重启！



```

# 2.是什么

## 2.1概念

规则引擎由[推理引擎](https://baike.baidu.com/item/推理引擎/6311829)发展而来，是一种嵌入在应用程序中的组件，实现了将业务决策从应用程序代码中分离出来，并使用预定义的语义模块编写业务决策。接受数据输入，解释业务规则，并根据业务规则做出业务决策

需要注意的是规则引擎并不是一个具体的技术框架，而是指的一类系统，即业务规则管理系统。目前市面上具体的规则引擎产品有：drools、VisualRules、iLog等

在很多企业的 IT 业务系统中，经常会有大量的业务规则配置，而且随着企业管理者的决策变化，这些业务规则也会随之发生更改。为了适应这样的需求，我们的 IT 业务系统应该能快速且低成本的更新。适应这样的需求，一般的作法是将业务规则的配置单独拿出来，使之与业务系统保持低耦合。目前，实现这样的功能的程序，已经被开发成为规则引擎。

## 2.2 起源

![img](E:\teaching\drools\\drools原理.png)

## 2.3 原理--基于 rete 算法的规则引擎

### 2.3.1 原理

在 AI 领域，产生式系统是一个很重要的理论，产生式推理分为正向推理和逆向推理产生式，其规则的一般形式是：IF 条件 THEN  操作。rete 算法是实现产生式系统中正向推理的高效模式匹配算法，通过形成一个 rete  网络进行模式匹配，利用基于规则的系统的时间冗余性和结构相似性特征 ，提高系统模式匹配效率

正向推理（Forward-Chaining）和反向推理（Backward-Chaining）

（1）正向推理也叫演绎法，由事实驱动，从一个初始的事实出发，不断地从应用规则得出结论。首先在候选队列中选择一条规则作为启用规则进行推理，记录其结论作为下一步推理的证据。如此重复这个过程，直到再无可用规则可被选用或者求得了所要求的解为止。

（2）反向推理也叫归纳法，由目标驱动，首先提出某个假设，然后寻找支持该假设的证据，若所需的证据都能找到，说明原假设是正确的，若无论如何都找不到所需要的证据，则说明原假设不成立，此时需要另作新的假设。



### 2.3.2 rete算法

Rete 算法最初是由卡内基梅隆大学的 Charles L.Forgy 博士在 1974 年发表的论文中所阐述的算法 ,  该算法提供了专家系统的一个高效实现。自 Rete 算法提出以后 , 它就被用到一些大型的规则系统中 , 像 ILog、Jess、JBoss  Rules 等都是基于 RETE 算法的规则引擎 。

Rete 在拉丁语中译为”net”，即网络。Rete 匹配算法是一种进行大量模式集合和大量对象集合间比较的高效方法，通过网络筛选的方法找出所有匹配各个模式的对象和规则。

其核心思想是将分离的匹配项根据内容动态构造匹配树，以达到显著降低计算量的效果。Rete 算法可以被分为两个部分：规则编译和规则执行 。当 Rete 算法进行事实的断言时，包含三个阶段：匹配、选择和执行，称做 match-select-act cycle。



## 2.4 规则引擎应用场景



| 业务领域     | 示例                               |
| ------------ | ---------------------------------- |
| 财务决策     | 贷款发放，征信系统                 |
| 库存管理     | 及时的供应链路                     |
| 票价计算     | 航空，传播，火车及其他公共汽车运输 |
| 生产采购系统 | 产品原材料采购管理                 |
| 风控系统     | 风控规则计算                       |
| 促销平台系统 | 满减，打折，加价购                 |

## 2.5 Drools 介绍

Drools 具有一个易于访问企业策略、易于调整以及易于管理的开源业务 [规则引擎](http://baike.baidu.com/view/1636209.htm)，符合业内标准，速度快、效率高。业务分析师或审核人员可以利用它轻松查看业务规则，从而检验已编码的规则是否执行了所需的业务规则。其前身是  Codehaus 的一个开源项目叫 Drools，后被纳入 JBoss 门下，更名为 JBoss Rules，成为了 JBoss  应用服务器的规则引擎。

Drools 被分为两个主要的部分：编译和运行时。编译是将规则描述文件按 ANTLR 3  语法进行解析，对语法进行正确性的检查，然后产生一种中间结构“descr”，descr 用 AST 来描述规则。目前，Drools  支持四种规则描述文件，分别是：drl 文件、 xls 文件、brl 文件和 dsl 文件，其中，常用的描述文件是 drl 文件和 xls  文件，而 xls 文件更易于维护，更直观，更为被业务人员所理解。运行时是将 AST传到 PackageBuilder，由  PackagBuilder来产生 RuleBase，它包含了一个或多个 Package 对象。



# 3 .消费赠送积分案例

![img](https://img2018.cnblogs.com/blog/725429/201906/725429-20190610095733992-1966819355.jpg)

上图为实际用法：



## 3.1 第一步： 创建工程，引入jar

> 由于当前java开发，普通使用springboot ，本课程以springboot为基本框架演示



jar 依赖，注意，排除spring相关依赖

```java
<!-- 规则引擎 -->
        <dependency>
            <groupId>org.kie</groupId>
            <artifactId>kie-spring</artifactId>
            <version>${drools.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-tx</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-beans</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-core</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-context</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```

## 3.2 创建 drools 自动配置类

drools 在spring 或者springboot中用法一样，其实就是创建好一些bean

```
package com.mashibing.drools.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;


/**
 * <p> 规则引擎自动配置类 </p>
 * @author 孙志强
 * @date 2019/9/10 11:20
 */
@Configuration
public class DroolsAutoConfiguration {

    private static final String RULES_PATH = "rules/";

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }

    @Bean
    @ConditionalOnMissingBean(KieContainer.class)
    public KieContainer kieContainer() throws IOException {
        final KieRepository kieRepository = getKieServices().getRepository();

        kieRepository.addKieModule(() -> kieRepository.getDefaultReleaseId());

        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();

        KieContainer kieContainer = getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());

        return kieContainer;
    }

    @Bean
    @ConditionalOnMissingBean(KieBase.class)
    public KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }

}
```





## 3.2订单实体类

```java
@Data
@Accessors(chain = true)
public class Order {

    /**
     * 订单原价金额
     */
    private int price;

    /**
     *下单人
     */
    private User user;

    /**
     *积分
     */
    private int score;

    /**
     * 下单日期
     */
    private Date bookingDate;
} 
```

## 3.3规则引擎文件

```java
package rules

import com.mashibing.drools.entity.Order

rule "zero"
    no-loop true
    lock-on-active true
    salience 1
    when
        $s : Order(amout <= 100)
    then
        $s.setScore(0);
        update($s);
end

rule "add100"
    no-loop true
    lock-on-active true
    salience 1
    when
        $s : Order(amout > 100 && amout <= 500)
    then
        $s.setScore(100);
        update($s);
end

rule "add500"
    no-loop true
    lock-on-active true
    salience 1
    when
        $s : Order(amout > 500 && amout <= 1000)
    then
        $s.setScore(500);
        update($s);
end

rule "add1000"
    no-loop true
    lock-on-active true
    salience 1
    when
        $s : Order(amout > 1000)
    then
        $s.setScore(1000);
        update($s);
end
```

## 3.4客户端 

```java
/**
 * 需求
 * 计算额外积分金额 规则如下: 订单原价金额
 * 100以下, 不加分
 * 100-500 加100分
 * 500-1000 加500分
 * 1000 以上 加1000分
 */
public class DroolsOrderTests extends DroolsApplicationTests {
    @Resource
    private KieContainer kieContainer;

    @Test
    public void Test() throws Exception {
        List<Order> orderList = getInitData();
        for (Order order : orderList) {
            if (order.getAmout() <= 100) {
                order.setScore(0);
                addScore(order);
            } else if (order.getAmout() > 100 && order.getAmout() <= 500) {
                order.setScore(100);
                addScore(order);
            } else if (order.getAmout() > 500 && order.getAmout() <= 1000) {
                order.setScore(500);
                addScore(order);
            } else {
                order.setScore(1000);
                addScore(order);
            }
        }
    }

    @Test
    public void droolsOrderTest() throws Exception {
        KieSession kieSession = kieContainer.newKieSession();
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
            order.setAmout(80);
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
            order.setAmout(200);
            order.setBookingDate(df.parse("2015-07-02"));
            User user = new User();
            user.setLevel(2);
            user.setName("Name2");
            order.setUser(user);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmout(800);
            order.setBookingDate(df.parse("2015-07-03"));
            User user = new User();
            user.setLevel(3);
            user.setName("Name3");
            order.setUser(user);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmout(1500);
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
```

## 3.5 drools  开发小结

### 3.5.1 drools 组成



drools规则引擎由以下几部分构成：

- Working Memory（工作内存）
- Rules（规则库）
- Facts
-  **Production memory** 
-  **Working memory:** 
-  **Agenda** 

如下图所示：



![1623341729607](E:\teaching\drools\engines.png)



#### 3.5.2 相关概念说明

**Working Memory**：工作内存，drools规则引擎会从Working Memory中获取数据并和规则文件中定义的规则进行模式匹配，所以我们开发的应用程序只需要将我们的数据插入到Working Memory中即可，例如本案例中我们调用kieSession.insert(order)就是将order对象插入到了工作内存中。

**Fact**：事实，是指在drools 规则应用当中，将一个**普通的JavaBean插入到Working Memory后的对象**就是Fact对象，例如本案例中的Order对象就属于Fact对象。Fact对象是我们的应用和规则引擎进行数据交互的桥梁或通道。

**Rules**：规则库，我们在规则文件中定义的规则都会被加载到规则库中。

**Pattern Matcher**：匹配器，将Rule Base中的所有规则与Working Memory中的Fact对象进行模式匹配，匹配成功的规则将被激活并放入Agenda中。

**Agenda**：议程，用于存放通过匹配器进行模式匹配后被激活的规则。

#### 3.5.3 规则引擎执行过程



#### 3.5.4 KIE介绍



在上述分析积分兑换的过程中，简单地使用了 "kie "开头的一些类名，**Kie全称为Knowledge Is Everything**，即"知识就是一切"的缩写，是Jboss一系列项目的总称。官网描述：这个名字渗透在GitHub账户和Maven POMs中。随着范围的扩大和新项目的展开，KIE（Knowledge Is Everything的缩写）被选为新的组名。KIE的名字也被用于系统的共享方面；如统一的构建、部署和使用。

![](E:\teaching\drools\kie.png)





# 4.规则文件开发

### 4.1 规则文件构成

在使用Drools时非常重要的一个工作就是编写规则文件，通常规则文件的后缀为.drl。

**drl是Drools Rule Language的缩写**。在规则文件中编写具体的规则内容。

一套完整的规则文件内容构成如下：

| 关键字   | 描述                                                         |
| :------- | :----------------------------------------------------------- |
| package  | 包名，只限于逻辑上的管理，同一个包名下的查询或者函数可以直接调用 |
| import   | 用于导入类或者静态方法                                       |
| global   | 全局变量                                                     |
| function | 自定义函数                                                   |
| query    | 查询                                                         |
| rule end | 规则体                                                       |

Drools支持的规则文件，除了drl形式，还有Excel文件类型的。

### 4.2 规则体语法结构

规则体是规则文件内容中的重要组成部分，是进行业务规则判断、处理业务结果的部分。

规则体语法结构如下：

```java
rule "ruleName"
    attributes
    when
        LHS 
    then
        RHS
end
```

**rule**：关键字，表示规则开始，参数为规则的唯一名称。

**attributes**：规则属性，是rule与when之间的参数，为可选项。

**when**：关键字，后面跟规则的条件部分。

**LHS**(Left Hand Side)：是规则的条件部分的通用名称。它由零个或多个条件元素组成。**如果LHS为空，则它将被视为始终为true的条件元素**。  （左手边）

**then**：关键字，后面跟规则的结果部分。

**RHS**(Right Hand Side)：是规则的后果或行动部分的通用名称。 （右手边）

**end**：关键字，表示一个规则结束。



### 4.3 注释

在drl形式的规则文件中使用注释和Java类中使用注释一致，分为单行注释和多行注释。

单行注释用"//"进行标记，多行注释以"/*"开始，以"*/"结束。如下示例：

```drl
//规则rule1的注释，这是一个单行注释
rule "rule1"
    when
    then
        System.out.println("rule1触发");
end

/*
规则rule2的注释，
这是一个多行注释
*/
rule "rule2"
    when
    then
        System.out.println("rule2触发");
end
```



### 4.4 Pattern模式匹配

前面我们已经知道了Drools中的匹配器可以将Rule Base中的所有规则与Working Memory中的Fact对象进行模式匹配，那么我们就需要在规则体的LHS部分定义规则并进行模式匹配。LHS部分由一个或者多个条件组成，条件又称为pattern。

**pattern的语法结构为：绑定变量名:Object(Field约束)**

其中绑定变量名可以省略，通常绑定变量名的命名一般建议以$开始。如果定义了绑定变量名，就可以在规则体的RHS部分使用此绑定变量名来操作相应的Fact对象。Field约束部分是需要返回true或者false的0个或多个表达式。



例如我们的入门案例中：

```java
rule "add100"
    no-loop true
    lock-on-active true
    salience 1
    when
        $order : Order(price > 100 && price <= 500)
    then
        $order.setScore(100);
        update($s);
end
```

通过上面的例子我们可以知道，匹配的条件为：

1、工作内存中必须存在Order这种类型的Fact对象-----类型约束

2、Fact对象的price属性值必须大于100------属性约束

3、Fact对象的price属性值必须小于等于500------属性约束

以上条件必须同时满足当前规则才有可能被激活。

 

**绑定变量既可以用在对象上，也可以用在对象的属性上**。例如上面的例子可以改为：

```java
rule "add100"
    no-loop true
    lock-on-active true
    salience 1
    when
        $order : Order($price:price > 100 && amopriceut <= 500)
    then
        System.out.println("$price=" + $price);
        $s.setScore(100);
        update($s);
end
```



LHS部分还可以定义多个pattern，多个pattern之间可以使用and或者or进行连接，也可以不写，默认连接为and。

```java
rule "add100"
    no-loop true
    lock-on-active true
    salience 1
    when
        $order : Order(price > 100 && price <= 500) and
        $user : User(level>3)
    then
        System.out.println($order.getUser());
        $order.setScore(100);
        update($order);
end
```



### 4.5 比较操作符

Drools提供的比较操作符，如下表：

| 符号         | 说明                                                         |
| :----------- | :----------------------------------------------------------- |
| >            | 大于                                                         |
| <            | 小于                                                         |
| >=           | 大于等于                                                     |
| <=           | 小于等于                                                     |
| ==           | 等于                                                         |
| !=           | 不等于                                                       |
| contains     | 检查一个Fact对象的某个属性值是否包含一个指定的对象值         |
| not contains | 检查一个Fact对象的某个属性值是否不包含一个指定的对象值       |
| memberOf     | 判断一个Fact对象的某个属性是否在一个或多个集合中             |
| not memberOf | 判断一个Fact对象的某个属性是否不在一个或多个集合中           |
| matches      | 判断一个Fact对象的属性是否与提供的标准的Java正则表达式进行匹配 |
| not matches  | 判断一个Fact对象的属性是否不与提供的标准的Java正则表达式进行匹配 |

前6个比较操作符和Java中的完全相同，下面我们重点学习后6个比较操作符。

#### 4.5.1 语法

- **contains | not contains语法结构**

  Object(Field[Collection/Array] contains value)

  Object(Field[Collection/Array] not contains value)

- **memberOf | not memberOf语法结构**

  Object(field memberOf value[Collection/Array])

  Object(field not memberOf value[Collection/Array])

- **matches | not matches语法结构**

  Object(field matches "正则表达式")

  Object(field not matches "正则表达式")



contain是前面包含后面，memberOf是后面包含前面。

#### 4.5.2 操作步骤

第一步：创建实体类，用于测试比较操作符

```java
package com.mashibing.drools.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author sunzhiqiang23
 * @date 2021-06-16 21:11
 */

@Data
@Accessors(chain = true)
public class ComparisonEntity {

    /**
     *名字集合
     */
    private String names;

    /**
     * 字符串集合
     */
    private List<String> list;

}

```

第二步：在/resources/rules下创建规则文件comparison.drl

```drl
package rules
import com.mashibing.drools.entity.ComparisonEntity

/*
 用于测试Drools提供的比较操作符
*/

//测试比较操作符contains
rule "rule_comparison_contains"
    when
        ComparisonEntity(names contains "张三")
        ComparisonEntity(list contains names)
    then
        System.out.println("规则rule_comparison_contains触发");
end

//测试比较操作符not contains
rule "rule_comparison_notContains"
    when
        ComparisonEntity(names not contains "张三")
        ComparisonEntity(list not contains names)
    then
        System.out.println("规则rule_comparison_notContains触发");
end

//测试比较操作符memberOf
rule "rule_comparison_memberOf"
    when
        ComparisonEntity(names memberOf list)
    then
        System.out.println("规则rule_comparison_memberOf触发");
end

//测试比较操作符not memberOf
rule "rule_comparison_notMemberOf"
    when
        ComparisonEntity(names not memberOf list)
    then
        System.out.println("规则rule_comparison_notMemberOf触发");
end

//测试比较操作符matches
rule "rule_comparison_matches"
    when
        ComparisonEntity(names matches "张.*")
    then
        System.out.println("规则rule_comparison_matches触发");
end

//测试比较操作符not matches
rule "rule_comparison_notMatches"
    when
        ComparisonEntity(names not matches "张.*")
    then
        System.out.println("规则rule_comparison_notMatches触发");
end
```



第三步：编写单元测试

```java
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
    public void testComparison(){
        KieSession kieSession = kieBase.newKieSession();
        ComparisonEntity comparisonEntity = new ComparisonEntity();
        comparisonEntity.setNames("张三");
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        comparisonEntity.setList(list);

        kieSession.insert(comparisonEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}

```



### 4.6 执行指定规则

通过前面的案例可以看到，我们在调用规则代码时，满足条件的规则都会被执行。那么如果我们只想执行其中的某个规则如何实现呢？

Drools给我们提供的方式是通过规则过滤器来实现执行指定规则。对于规则文件不用做任何修改，只需要修改Java代码即可，如下：

```java

//通过规则过滤器实现只执行指定规则
kieSession.fireAllRules(new kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_filter_1"));

```



### 4.7 关键字

Drools的关键字分为：硬关键字(Hard keywords)和软关键字(Soft keywords)。

**硬关键字是我们在规则文件中定义包名或者规则名时明确不能使用的，否则程序会报错**。软关键字虽然可以使用，但是不建议使用。

硬关键字包括：true false null

软关键字包括：lock-on-active date-effective date-expires no-loop auto-focus activation-group agenda-group ruleflow-group entry-point duration package import dialect salience enabled attributes rule extend when then template query declare function global eval not in or and exists forall accumulate collect from action reverse result end over init



```java
比如：
rule true  //不可以
rule "true" 可以
```

## 5. 规则属性  attributes

前面我们已经知道了规则体的构成如下：

```java
rule "ruleName"
    attributes
    when
        LHS
    then
        RHS
end
```

本章节就是针对规则体的**attributes**属性部分进行讲解。Drools中提供的属性如下表(部分属性)：

| 属性名           | 说明                                               |
| :--------------- | :------------------------------------------------- |
| salience         | 指定规则执行优先级                                 |
| dialect          | 指定规则使用的语言类型，取值为java和mvel           |
| enabled          | 指定规则是否启用                                   |
| date-effective   | 指定规则生效时间                                   |
| date-expires     | 指定规则失效时间                                   |
| activation-group | 激活分组，具有相同分组名称的规则只能有一个规则触发 |
| agenda-group     | 议程分组，只有获取焦点的组中的规则才有可能触发     |
| timer            | 定时器，指定规则触发的时间                         |
| auto-focus       | 自动获取焦点，一般结合agenda-group一起使用         |
| no-loop          | 防止死循环，防止自己更新规则再次触发               |
| lock-on-active   | no-loop增强版本。可防止别人更新规则再次出发        |

### 5.1 enabled属性

enabled属性对应的取值为true和false，默认值为true。

用于指定当前规则是否启用，如果设置的值为false则当前规则无论是否匹配成功都不会触发

```java
package rules
import com.mashibing.drools.entity.AttributesEnabledEntity

/*
 用于测试Drools 属性:enabled
*/

//测试enabled
rule "rule_attributes_enabled"
    enabled false
    when
        AttributesEnabledEntity(num > 10)
    then
        System.out.println("规则rule_attributes_enabled触发");
end
```

### 5.2 dialect属性

dialect属性用于指定当前规则使用的语言类型，取值为java和mvel，默认值为java。

注：mvel是一种基于java语法的表达式语言。

虽然mvel吸收了大量的java语法，但作为一个表达式语言，还是有着很多重要的不同之处，以达到更高的效率，比如：mvel像正则表达式一样，有直接支持集合、数组和字符串匹配的操作符。

 除了表达式语言外，mvel还提供了用来配置和构造字符串的模板语言。

mvel2.x表达式包含以下部分的内容：属性表达式，布尔表达式，方法调用，变量赋值，函数定义

### 5.3 salience属性

salience属性用于指定规则的执行优先级，**取值类型为Integer**。**数值越大越优先执行**。每个规则都有一个默认的执行顺序，如果不设置salience属性，规则体的执行顺序为由上到下。

drl文件内容如下：

```
package rules
import com.mashibing.drools.entity.AttributesSalienceEntity

/*
 用于测试Drools 属性:salience
*/

rule "rule_attributes_salience_1"
    when
        AttributesSalienceEntity(flag == true)
    then
        System.out.println("规则 rule_attributes_salience_1 触发");
end

rule "rule_attributes_salience_2"
    when
        AttributesSalienceEntity(flag == true)
    then
        System.out.println("规则 rule_attributes_salience_2 触发");
end

rule "rule_attributes_salience_3"
    when
        AttributesSalienceEntity(flag == true)
    then
        System.out.println("规则 rule_attributes_salience_3 触发");
end
```



通过控制台可以看到，由于以上三个规则没有设置salience属性，所以执行的顺序是按照规则文件中规则的顺序由上到下执行的。接下来我们修改一下文件内容：

```java
package rules
import com.mashibing.drools.entity.AttributesSalienceEntity

/*
 用于测试Drools 属性:salience
*/

rule "rule_attributes_salience_1"
    salience 10
    when
        AttributesSalienceEntity(flag == true)
    then
        System.out.println("规则 rule_attributes_salience_1 触发");
end

rule "rule_attributes_salience_2"
    salience 20
    when
        AttributesSalienceEntity(flag == true)
    then
        System.out.println("规则 rule_attributes_salience_2 触发");
end

rule "rule_attributes_salience_3"
    salience 1
    when
        AttributesSalienceEntity(flag == true)
    then
        System.out.println("规则 rule_attributes_salience_3 触发");
end
```



通过控制台可以看到，规则文件执行的顺序是按照我们设置的salience值由大到小顺序执行的。

建议在编写规则时使用salience属性明确指定执行优先级。

### 5.4 no-loop属性

no-loop属性用于防止死循环，当规则通过update之类的函数修改了Fact对象时，可能使当前规则再次被激活从而导致死循环。取值类型为Boolean，默认值为false。测试步骤如下：

第一步：编写规则文件

```
package rules
import com.mashibing.drools.entity.AttributesNoLoopEntity

/*
 用于测试Drools 属性:no-loop
*/

rule "rule_attributes_noloop"
    //no-loop true
    when
        $attributesNoLoopEntity:AttributesNoLoopEntity(num > 1)
    then
        update($attributesNoLoopEntity)
        System.out.println("规则 rule_attributes_noloop 触发");
end
```



第二步：编写单元测试

```java
    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        AttributesNoLoopEntity attributesNoLoopEntity = new AttributesNoLoopEntity();
        attributesNoLoopEntity.setNum(20);

        kieSession.insert(attributesNoLoopEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
```



通过控制台可以看到，由于我们没有设置no-loop属性的值，所以发生了死循环。接下来设置no-loop的值为true再次测试则不会发生死循环。

### 5.5 activation-group属性

activation-group属性是指**激活分组**，取值为String类型。具有相同分组名称的规则只能有一个规则被触发。

第一步：编写规则文件



```
package rules
import com.mashibing.drools.entity.AttributesActivationGroupEntity

/*
 用于测试Drools 属性: activation-group
*/

rule "rule_attributes_activation_group_1"
    activation-group "customGroup"
    when
        $attributesActivationGroupEntity:AttributesActivationGroupEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_activation_group_1 触发");
end

rule "rule_attributes_activation_group_2"
    activation-group "customGroup"
    when
        $attributesActivationGroupEntity:AttributesActivationGroupEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_activation_group_2 触发");
end

```



第二步：编写单元测试

```java
	@Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        AttributesActivationGroupEntity attributesActivationGroupEntity = new AttributesActivationGroupEntity();
        attributesActivationGroupEntity.setNum(20);

        kieSession.insert(attributesActivationGroupEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
```



通过控制台可以发现，上面的两个规则因为属于同一个分组，所以只有一个触发了。同一个分组中的多个规则如果都能够匹配成功，具体哪一个最终能够被触发可以通过salience属性确定。

### 5.6 agenda-group属性

agenda-group属性为**议程分组**，属于另一种可控的规则执行方式。用户可以通过设置agenda-group来控制规则的执行，只有获取焦点的组中的规则才会被触发。

第一步：编写规则文件



```java
package rules
import com.mashibing.drools.entity.AttributesAgendaGroupEntity

/*
 用于测试Drools 属性: agenda-group
*/

rule "rule_attributes_agenda_group_1"
    agenda-group "customAgendaGroup1"
    when
        $attributesAgendaGroupEntity:AttributesAgendaGroupEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_agenda_group_1 触发");
end

rule "rule_attributes_agenda_group_2"
    agenda-group "customAgendaGroup1"
    when
        $attributesAgendaGroupEntity:AttributesAgendaGroupEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_agenda_group_2 触发");
end


rule "rule_attributes_activation_group_3"
    agenda-group "customAgendaGroup2"
    when
        $attributesAgendaGroupEntity:AttributesAgendaGroupEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_activation_group_3 触发");
end

rule "rule_attributes_agenda_group_4"
    agenda-group "customAgendaGroup2"
    when
        $attributesAgendaGroupEntity:AttributesAgendaGroupEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_agenda_group_4 触发");
end

```



第二步：编写单元测试

```java
    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        AttributesAgendaGroupEntity attributesAgendaGroupEntity = new AttributesAgendaGroupEntity();
        attributesAgendaGroupEntity.setNum(20);

        kieSession.insert(attributesAgendaGroupEntity);
        kieSession.getAgenda().getAgendaGroup("customAgendaGroup2").setFocus();

        kieSession.fireAllRules();
        kieSession.dispose();
    }
```



通过控制台可以看到，只有获取焦点的分组中的规则才会触发。与activation-group不同的是，activation-group定义的分组中只能够有一个规则可以被触发，而agenda-group分组中的多个规则都可以被触发。

### 5.7 auto-focus属性

auto-focus属性为**自动获取焦点**，取值类型为Boolean，默认值为false。一般结合agenda-group属性使用，当一个议程分组未获取焦点时，可以设置auto-focus属性来控制。

第一步：编写规则文件

```java
package rules
import com.mashibing.drools.entity.AttributesAutoFocusEntity

/*
 用于测试Drools 属性: auto-focus
*/

rule "rule_attributes_auto_focus_1"
    agenda-group "customAgendaGroup1"
    when
        $attributesAutoFocusEntity:AttributesAutoFocusEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_auto_focus_1 触发");
end

rule "rule_attributes_auto_focus_2"
    agenda-group "customAgendaGroup1"
    when
        $attributesAutoFocusEntity:AttributesAutoFocusEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_auto_focus_2 触发");
end

rule "rule_attributes_auto_focus_3"
    agenda-group "customAgendaGroup2"
//    auto-focus true
    when
        $attributesAutoFocusEntity:AttributesAutoFocusEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_auto_focus_3 触发");
end

rule "rule_attributes_auto_focus_4"
    agenda-group "customAgendaGroup2"
    when
        $attributesAutoFocusEntity:AttributesAutoFocusEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_auto_focus_4 触发");
end
```



第二步：编写单元测试

```java
    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        AttributesAutoFocusEntity attributesAutoFocusEntity = new AttributesAutoFocusEntity();
        attributesAutoFocusEntity.setNum(20);

        kieSession.insert(attributesAutoFocusEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
```

通过控制台可以看到，设置auto-focus属性为true的规则都触发了。



注意：同一个组，只要有个设置auto-focus true 其他的设置不设置都无所谓啦。都会起作用的。

### 5.8 timer属性

timer属性可以通过定时器的方式指定规则执行的时间，使用方式有两种：

**方式一**：timer (int: <initial delay> <repeat interval>?)

此种方式遵循java.util.Timer对象的使用方式，第一个参数表示几秒后执行，第二个参数表示每隔几秒执行一次，第二个参数为可选。

**方式二**：timer(cron: <cron expression>) 

此种方式使用标准的unix cron表达式的使用方式来定义规则执行的时间。

第一步：编写规则文件

```java
package testtimer
import java.text.SimpleDateFormat
import java.util.Date
/*
    此规则文件用于测试timer属性
*/

rule "rule_timer_1"
    timer (5s 2s) //含义：5秒后触发，然后每隔2秒触发一次
    when
    then
        System.out.println("规则rule_timer_1触发，触发时间为：" + 
                         new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
end

rule "rule_timer_2"
    timer (cron:0/1 * * * * ?) //含义：每隔1秒触发一次
    when
    then
        System.out.println("规则rule_timer_2触发，触发时间为：" + 
                         new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
end
```



第二步：编写单元测试

```java
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
```



注意：如果规则中有用到了timer属性，匹配规则需要调用kieSession.fireUntilHalt();这里涉及一个规则引擎的执行模式和线程问题，关于具体细节，我们后续讨论。

### 5.9 date-effective属性

date-effective属性**用于指定规则的生效时间**，即只有当前系统时间大于等于设置的时间或者日期规则才有可能触发。默认日期格式为：dd-MMM-yyyy。用户也可以自定义日期格式。

第一步：编写规则文件

```
package rules
import com.mashibing.drools.entity.AttributesDateEffectiveEntity

/*
 用于测试Drools 属性: date-effective
*/

rule "rule_attributes_date_effective"
//    date-effective "20-七月-2021"
    date-effective "2021-02-20"
    when
        $attributesDateEffectiveEntity:AttributesDateEffectiveEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_date_effective 触发");
end


```



第二步：编写单元测试

```java
    @Test
    public void test(){
        KieSession kieSession = kieBase.newKieSession();
        AttributesDateEffectiveEntity attributesDateEffectiveEntity = new AttributesDateEffectiveEntity();
        attributesDateEffectiveEntity.setNum(20);

        kieSession.insert(attributesDateEffectiveEntity);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
```



注意：需要在VM参数上加上日期格式:-Ddrools.dateformat=yyyy-MM-dd，在生产环境所在规则引擎的JVM设置中，也需要设置此参数，以保证开发和生产的一致性。

### 5.10 date-expires属性

date-expires属性用于指定规则的**失效时间**，即只有当前系统时间小于设置的时间或者日期规则才有可能触发。默认日期格式为：dd-MMM-yyyy。用户也可以自定义日期格式。

第一步：编写规则文件/resource/rules/dateexpires.drl

```
package rules
import com.mashibing.drools.entity.AttributesDateExpiresEntity

/*
 用于测试Drools 属性: date-expires
*/

rule "rule_attributes_date_expires"
    date-expires "2021-06-20"
    when
        $attributesDateExpiresEntity:AttributesDateExpiresEntity(num > 1)
    then
        System.out.println("规则 rule_attributes_date_expires 触发");
end
```



第二步：编写单元测试

```java
@Test
public void test(){
    KieSession kieSession = kieBase.newKieSession();
    AttributesDateExpiresEntity attributesDateExpiresEntity = new AttributesDateExpiresEntity();
    attributesDateExpiresEntity.setNum(20);

    kieSession.insert(attributesDateExpiresEntity);

    kieSession.fireAllRules();
    kieSession.dispose();
}
```



注意：需要在VM参数上加上日期格式:-Ddrools.dateformat=yyyy-MM-dd，在生产环境所在规则引擎的JVM设置中，也需要设置此参数，以保证开发和生产的一致性。







参考文档：

【1】  百度百科：规则引擎 ：https://baike.baidu.com/item/%E8%A7%84%E5%88%99%E5%BC%95%E6%93%8E/3076955?fr=aladdin

【2】 开源规则引擎 drools：https://blog.csdn.net/sdmxdzb/article/details/81461744

【3】 drools官网：[Drools - Business Rules Management System (Java™, Open Source)](https://www.drools.org/#)

