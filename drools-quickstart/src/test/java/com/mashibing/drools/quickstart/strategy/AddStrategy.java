package com.mashibing.drools.quickstart.strategy;

/**
 * @author sunzhiqiang23
 * @date 2019/9/1118:20
 */
public class AddStrategy implements Strategy {

    @Override
    public int calc(int num1, int num2) {
        return num1 + num2;
    }
}
