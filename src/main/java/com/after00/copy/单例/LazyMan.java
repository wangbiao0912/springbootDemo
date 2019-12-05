package com.after00.copy.单例;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

//DCL懒汉式
public class LazyMan {
    private static boolean flag = false;
    private LazyMan(){
        synchronized (LazyMan.class){
            if (flag==false){
                flag = true;
            }else {
                throw new RuntimeException("不要试图使用反射破坏单例模式");
            }
        }
    }

    private volatile static LazyMan lazyMan;

    public static LazyMan getInstance(){
        if (lazyMan==null){
            synchronized (LazyMan.class){
                if (lazyMan == null){
                    lazyMan = new LazyMan();
                }
            }
        }
        return lazyMan;
    }

}

class Demo02{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        //LazyMan instance = LazyMan.getInstance();
        Field field = LazyMan.class.getDeclaredField("flag");
        field.setAccessible(true);

        Constructor<LazyMan> constructor = LazyMan.class.getDeclaredConstructor(null);
        constructor.setAccessible(true); //true

        LazyMan instance = constructor.newInstance();
        //flag = true;


        field.set(instance,false);
        LazyMan instance2 = constructor.newInstance();

        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());

    }
}



//指令重排
/*
    1. 分配内存
    2. 执行构造方法
    3. 指向地址

    A lazyMan = new LazyMan();  3. 指向地址
    B lazyMan，不完整的对象
 */
