package com.spring.springstudy.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlilai
 * @title: TestQuery
 * @projectName javaStudy1
 * @description:
 * @date 2019/11/110:07
 */
public class TestQuery<T> {


    private  Object[] entry;


    private static ReentrantLock lock = new ReentrantLock();  //锁

    private static Condition getcondition = lock.newCondition(); //查询
    private static Condition putcondition = lock.newCondition(); //添加锁


    private int size; //数组长度

    private  int index; //数组下标


    public TestQuery(int size){

        entry =new Object[size];
        this.size=size;
    }

    public boolean put(T t){

        lock.lock();
        try{
            if(index==size){
                try
                {
                    System.out.println("队列已满 阻塞了添加");
                    putcondition.await();
                    return false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            entry[index++] = t;
            size++;
            System.out.println("正在添加对象  "+t);
            System.out.println("index="+index);
            getcondition.signal();

        }catch (Exception e){

        }finally {
            lock.unlock();
        }
        return true;
    }

    public T get(){
        lock.lock();
        T o = null;
        try {
            if(index==0){
                try {
                    System.out.println("队列为空 阻塞了获取");
                    getcondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            o = (T)entry[--index];
            System.out.println("返回队列值 "+o);
            putcondition.signal();

            return o;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        return o;
    }


    public static void main(String[] args) {

        TestQuery testQuery=new TestQuery(3);

             new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int i = 0; i < 3; i++) {
                        testQuery.put(i);
                    }
                }
            }).start();


            new Thread(new Runnable() {
                @Override
                public void run() {

                   while (true){
                        testQuery.get();
                    }
                }
            }).start();

    }

}
