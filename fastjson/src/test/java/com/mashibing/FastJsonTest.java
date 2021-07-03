package com.mashibing;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

/**
 * @author sunzhiqiang23
 * @date 2021-07-03 21:42
 */
public class FastJsonTest {

    @Test
    public void test(){
        Student student = new Student();
        System.out.println(JSON.toJSONString(student));
    }

}
