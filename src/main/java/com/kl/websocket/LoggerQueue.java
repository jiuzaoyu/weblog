package com.kl.websocket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by kl on 2017/3/20.
 */
public class LoggerQueue {
    //队列大小
    public static final int QUEUE_MAX_SIZE = 10000;
    private static LoggerQueue alarmMessageQueue = new LoggerQueue();
    //用于进程内日志
    private BlockingQueue<LoggerMessage> blockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

    //用于进程外文件日志
    private BlockingQueue<String> fileLogBlockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

    /**
     * 消息入队
     * @param log
     * @return
     */
    public boolean pushFileLog(String log) {
        return this.fileLogBlockingQueue.add(log);//队列满了就抛出异常，不阻塞
    }
    /**
     * 消息出队
     * @return
     */
    public String pollFileLog() {
        String result = null;
        try {
            result = this.fileLogBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private LoggerQueue() {
    }

    public static LoggerQueue getInstance() {
        return alarmMessageQueue;
    }
    /**
     * 消息入队
     * @param log
     * @return
     */
    public boolean push(LoggerMessage log) {
        return this.blockingQueue.add(log);//队列满了就抛出异常，不阻塞
    }
    /**
     * 消息出队
     * @return
     */
    public LoggerMessage poll() {
        LoggerMessage result = null;
        try {
            result = this.blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
