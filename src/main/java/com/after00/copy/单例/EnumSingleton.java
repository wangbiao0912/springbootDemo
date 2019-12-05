package com.after00.copy.单例;

import java.lang.reflect.Constructor;

//知其然，并知其所以然！
//enum 本质还是一个类
public enum EnumSingleton {

    INSTANCE;

    public EnumSingleton getInstance(){
        return INSTANCE;
    }

}

class Demo04{
    public static void main(String[] args) throws Exception {
        EnumSingleton instance = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        System.out.println("正常情况下："+(instance==instance2)); //true


        //Constructor<EnumSingleton> declaredConstructor = EnumSingleton.class.getDeclaredConstructor();
        Constructor<EnumSingleton> declaredConstructor = EnumSingleton.class.getDeclaredConstructor(String.class,int.class);

        declaredConstructor.setAccessible(true);
        EnumSingleton enumSingleton = declaredConstructor.newInstance();

    }
}
