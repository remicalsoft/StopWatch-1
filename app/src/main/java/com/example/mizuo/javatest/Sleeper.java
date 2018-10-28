package com.example.mizuo.javatest;

public class Sleeper {
    public static void sleep(int msec){
        try{
            Thread.sleep(msec);
        }catch (Exception e){}
    }
}
