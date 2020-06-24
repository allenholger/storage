package com.example.storage.util;

import org.springframework.context.annotation.Bean;

/**
  * 本类是雪花算法的工具类，用来生成clientId
  *@author: Allen Holger
  *@since: 2020/6/10
  */
public class SnowFlakeUtils {

    /**
     * 起始的时间戳
     */
    private final long twepoch = 1580567416692L;

    /**
     * 每一部分占用的位数
     */
    //机器ID所占位置
    private final long workerIdbits = 5L;
    //数据中心所占位置
    private final long datacenterIdBits = 5L;
    //序列在ID中所占的位数
    private final long sequenceBits = 12L;

    /**
     *  每一部分的最大值
     */
    //支持的最大机器ID， 结果是31，（这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数）
    private final long maxWorkerId = -1L ^ (-1L << workerIdbits);
    //支持的最大数据中心ID， 结果是31
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    //支持的最大序列号
    private final long maxSequence = -1L ^ (-1 << sequenceBits);

    //机器ID向左移12位
    private final long workerIdShift = sequenceBits;
    //数据中心标识id左移17位(12 + 5)
    private final long datacenterIdShift = sequenceBits + workerIdbits;
    //时间戳左移22位(5 + 5 + 12)
    private final long timestampLeftShift = sequenceBits + workerIdbits + datacenterIdBits;

    //工作机器的ID（0~31）
    private long workerId;
    //数据中心的Id(0~31)
    private long datacenterId;
    //毫秒内序列(0~4095)
    private long sequence = 0L;
    //上次生成ID的时间戳
    private long lastTimestamp = -1L;

    public SnowFlakeUtils(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public SnowFlakeUtils() {
        this(0, 0);
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return
     */
    public synchronized long nextId() {
        long timestamp = currentTime();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if(timestamp < lastTimestamp){
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            //当前时间毫秒内，序列号自增
            sequence = (sequence + 1) & maxSequence;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {//时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 阻塞到下一毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long currentTime = currentTime();
        while (currentTime <= lastTimestamp) {
            currentTime = currentTime();
        }
        return currentTime;
    }


    /**
     * 返回当前时间的毫秒值
     *
     * @return
     */
    private long currentTime() {
        return System.currentTimeMillis();
    }

}
