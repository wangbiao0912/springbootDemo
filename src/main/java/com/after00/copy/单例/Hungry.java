package com.after00.copy.单例;

//饿汉式
public class Hungry {

    private byte[] data1 = new byte[1024];
    private byte[] data2 = new byte[1024];
    private byte[] data3 = new byte[1024];
    private byte[] data4 = new byte[1024];

    //避免new-
    private Hungry() {
    }

    //类一加载就初始化对象
    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance(){
        return HUNGRY;
    }

}


class Demo01{
    public static void main(String[] args) {
        Hungry instance = Hungry.getInstance();
        Hungry instance2 = Hungry.getInstance();

        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());

    }
}
