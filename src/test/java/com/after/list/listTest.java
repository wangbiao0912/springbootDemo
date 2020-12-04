package com.after.list;

import java.util.Arrays;
import java.util.List;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.Test;

public class listTest {

  @Test
  public static void main(String[] args)  {
//    List list = new ArrayList();
//    list.add("1");
//    list.add("3");
//    list.add("3");
//    list.add("1");
//    list.add("1");
//    list.add("1"); 
//
//    list.add("3");
////    list.add("9");
////    list.add("5");
//
//    for (Object o : list) {
//      if ("3".equals(o)) {
//        list.remove(o);
//      }
//    }
//    System.out.println(list);
//MetaObject是一个Mybatis里面的一个封装对象的工具类，封装后可以使用类似OGNL一样操作对象的属性，这个我从Mybatis里单独提取出来放github上了，有兴趣的可以玩玩。
//这个不是今天的重点，此处可以忽略。
    List<String> ls = Arrays.asList("1", "2", "3");
    MetaObject metaObject = SystemMetaObject.forObject(ls);
    metaObject.add("4");
    System.out.println(ls);
  }
}
