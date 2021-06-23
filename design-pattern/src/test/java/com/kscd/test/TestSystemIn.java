package com.kscd.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestSystemIn {
    
    /** 从控制台输入的字符流中读取文本并缓冲字符，以便有效的读取字符，数组，行 */
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * 测试System.in
     * @param args main方法参数
     */
    public static void main(String[] args) throws IOException {
        System.out.println("姓名：");
        String name = reader.readLine();
        
        System.out.println("年龄：");
        int age = Integer.parseInt(reader.readLine());

        System.out.println(name + "-审核通过，年龄:" + age);
    }
    
}
