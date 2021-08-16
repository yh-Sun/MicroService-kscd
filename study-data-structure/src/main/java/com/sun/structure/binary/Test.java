package com.sun.structure.binary;

public class Test {
    public static void main(String[] args) {
        int i = 0b1111111;

        String iBinaryString = Integer.toBinaryString(i);
        System.out.println(iBinaryString);

        int i1 = Integer.parseInt(iBinaryString, 2);
        System.out.println(i1);

        // 男：0b10   女：0b00  左移的位数从0开始，每一位都代表不同的boolean含义，本例中：i = 1为区分男女
        int boy = i | (1 << 1);
        int girl = i & ~(1 << 1);
        String boyS = Integer.toBinaryString(boy);
        String girlS = Integer.toBinaryString(girl);
    }
}
