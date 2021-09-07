package com.sun.mongo.util.io.buffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * buffer的创建demo
 */
public class CreateBufferDemo {

    public static void main(String[] args) {
        // 无内容的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(5);
        for (int i = 0; i < 5; i++) {
            byte b = allocate.get();
            System.out.println(b);
        }

        // 有内容的缓冲区
        ByteBuffer wrap = ByteBuffer.wrap("sun".getBytes(StandardCharsets.UTF_8));
        byte b = wrap.get();
        System.out.println(b);
    }

}
