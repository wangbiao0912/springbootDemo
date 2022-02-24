package com.after;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class test1 {
    @Test
    public  void test1(){
        LinkedList<Integer> linkedList    = new LinkedList<Integer>();
        List<Integer> synchronizedList =  Collections.synchronizedList(linkedList);
    }
}