package com.mashibing.drools.quickstart.strategy;

/**
 * @author sunzhiqiang23
 * @title: Environment
 * @projectName drools-demo
 * @description: TODO
 * @date 2019/9/1118:22
 */
public class Environment {
    private Strategy strategy;

    public Environment(Strategy strategy) {
        this.strategy = strategy;
    }

    public int calulate(int num1, int num2) {
        return strategy.calc(num1, num2);
    }
}
