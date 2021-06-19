package com.mashibing.drools.quickstart.strategy;

/**
 * @author sunzhiqiang23
 * @title: AddStrategy
 * @projectName drools-demo
 * @description: TODO
 * @date 2019/9/1118:20
 */
public class SubstractStrategy implements Strategy {

    @Override
    public int calc(int num1, int num2) {
        return num1 - num2;
    }
}
