package com.spring.springstudy.thread;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author chenlilai
 * @title: Test
 * @projectName javaStudy1
 * @description:
 * @date 2019/11/815:05
 */
public class Test {


    public static void main(String[] args) {

        String []str={"A","B","C","D","E","F","G",};

        String[] str1={"1","2","3","4","5","6"};

        String[] strings1 = Arrays.stream(str)
                .map(a -> Arrays.stream(str1).map(b -> a + b).collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .toArray(String[]::new);
        for(String s:strings1){
            System.out.println(s);
        }
        System.out.println(strings1.length);
    }
}
