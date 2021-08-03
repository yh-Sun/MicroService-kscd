package com.sun.leetcode;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ModifierUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 循环判断Bean是否为空。如果bean里是List/Array则判断List/Array里面的是否为空
 *
 * @author  Sun
 * @date    2021/8/3 11:11
 * @since   1.0
 */
public class BeanIsEmpty {

    public static void main(String[] args) {
        A a = new A();
        List<A> arrayList = new ArrayList<>();
        A a3 = new A();
        a3.setList(new ArrayList<>());
        a3.setS("1");
        arrayList.add(a3);
        a.setList(arrayList);

        A a1 = new A();
        A a2 = new A();
        a.setArray(new A[] {a1, a2});
        boolean empty = isEmpty(a);

        System.out.println(empty);
    }

    @Data
    static class A {
        private String s;

        private List<A> list;

        private A[] array;
    }

    public static boolean isEmpty(Object bean, String... ignoreFiledNames) {
        if (null != bean) {
            Field[] var2 = ReflectUtil.getFields(bean.getClass());
            for (Field field : var2) {
                if (!ModifierUtil.isStatic(field) && !ArrayUtil.contains(ignoreFiledNames, field.getName())) {
                    Object fieldValue = ReflectUtil.getFieldValue(bean, field);
                    if (null != fieldValue) {
                        if(fieldValue instanceof CharSequence) {
                            if(StrUtil.isNotBlank((CharSequence) fieldValue)) {
                                return false;
                            }
                        } else if (fieldValue instanceof Collection) {
                            if (CollUtil.isNotEmpty((Collection<?>) fieldValue)) {
                                for (Object o : (Collection<?>) fieldValue) {
                                    if (!isEmpty(o)) {
                                        return false;
                                    }
                                }
                            }
                        } else if (fieldValue.getClass().isArray()) {
                            if (ArrayUtil.isNotEmpty(fieldValue)) {
                                for (int i = 0; i < Array.getLength(fieldValue); i++) {
                                    if(!isEmpty(Array.get(fieldValue, i))) {
                                        return false;
                                    }
                                }
                            }
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
