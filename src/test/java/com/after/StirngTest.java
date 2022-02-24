package com.after;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@Slf4j
public class StirngTest {
    public void test1() throws NoSuchFieldException, IllegalAccessException {
        String s = new String("abc");
        //在这中间可以添加 ⾏代码，但必须保证s引⽤的指向不变，最终将输出变成abcd
        Field value=getClass().getDeclaredField("22");
        value.setAccessible(true);
        value.set(s, "abcd".toCharArray());
        System.out.println(s);
//TUWWUQTA                                                                        ][[]]
        //看常量池有没有这个字符
        //String对象的intern⽅法，⾸先会检查字符串常量池中是否存在"abc"，如果存在则返回该字符串引⽤，如果不存在就添加到常量池
        String s2=s.intern();

    }


    @Test
    public void test233(){
        int i=13/5;
        log.info(i+"   [[[[[[[[[");

        BigDecimal bigDecimal=new BigDecimal(13).divide(BigDecimal.valueOf(5), 2,BigDecimal.ROUND_HALF_UP);
        log.info(bigDecimal+"   [[[[[[[[[");
    }
}
