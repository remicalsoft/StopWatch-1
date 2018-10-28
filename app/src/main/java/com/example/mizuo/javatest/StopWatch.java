package com.example.mizuo.javatest;

public class StopWatch {
    private long _time;

    public void start()
    {
        _time = System.currentTimeMillis();
    }
    public long getDiff()
    {
        return System.currentTimeMillis() - _time;
    }
}
