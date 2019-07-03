package com.spring.springstudy.thread;

/**
 * @title: TestThread
 * @projectName javaStudy1
 * @description:
 * @date 2019/7/115:16
 */
public class TestThread extends  Thread {

    private static volatile int i=1;

    ThreadLocal<Integer> th=new ThreadLocal<Integer>() {
        protected Integer initialValue() {

            return i;
        }
    };

    @Override
    public void run() {

        i = th.get()+1;
        System.out.println(i);
    }
    //    };

    //        }
    public static void main(String[] args){
        new TestThread().start();
        new TestThread().start();
        new TestThread().start();


    }

}
