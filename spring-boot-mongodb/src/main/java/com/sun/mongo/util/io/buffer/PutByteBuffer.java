package com.sun.mongo.util.io.buffer;

import cn.hutool.core.lang.func.VoidFunc0;

import java.nio.ByteBuffer;

/**
 * 想缓冲区添加数据
 */
public class PutByteBuffer {

    public static void main(String[] args) throws Exception {
        // 创建一个缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(10);

        VoidFunc0 func = () -> {
            // 获取缓冲区的当前位置
            int position = allocate.position();
            // 获取此缓冲区的容量
            int capacity = allocate.capacity();
            // 获取此缓冲区的限制
            int limit = allocate.limit();
            // 获取当前位置和限制之间的元素数
            int remaining = allocate.remaining();
            System.out.println(position + "-" + capacity + "-" + limit + "-" + remaining);
        };
        func.call();

        // 修改当前位置
        allocate.position(1);
        // 修改缓冲区最大限制
        allocate.limit(9);
        func.call();

        // 添加一个字节
        allocate.put((byte) 97);
        func.call();

        // 是否存在元素
        boolean b = allocate.hasRemaining();

        // 读取缓冲区，以字节码形式返回
        byte[] array = allocate.array();
        System.out.println(new String(array));

        byte b1 = allocate.get();
        func.call();

        // 写模式
        allocate.clear();
        allocate.put((byte)1);
        func.call();

    }

}
