package com.after00.copy.单例;

public class Holder {

    private Holder(){

    }

    public static Holder getInstance(){
        return InnerClass.HOLDER;
    }

    private static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }

}
