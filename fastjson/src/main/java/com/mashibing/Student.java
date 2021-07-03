package com.mashibing;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.nio.ByteBuffer;

/**
 * @author sunzhiqiang23
 * @date 2021-07-03 21:42
 */

@Data
public class Student {

    private String getName(){
        return "张三";
    }

    public ByteBuffer getByteBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.put("str1".getBytes());
        return byteBuffer;
    }
}
