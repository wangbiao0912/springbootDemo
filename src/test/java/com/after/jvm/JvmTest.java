package com.after.jvm;

import lombok.SneakyThrows;

import java.util.ArrayList;

public class JvmTest {


    @SneakyThrows
    public static void main(String[] args) {
        int i=0;
        ArrayList<JvmTest> arrayList =new ArrayList<>();
        while (i<1000000000){
            i++;
            arrayList.add(new JvmTest());
            Thread.sleep(1);
        }
    }


}
