package com.kl.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class FileLogListening {

    private long lastTimeFileSize = 0;  //上次文件大小

    @Autowired
    Environment environment;

    /**
     * 监听日志文件
     *
     * @throws IOException
     */
    @PostConstruct
    public void start() throws IOException {
        String logPath = environment.getProperty("weblogPath");
        File logFile = ResourceUtils.getFile(logPath);
        //指定文件可读可写     
        final RandomAccessFile randomFile = new RandomAccessFile(logFile, "rw");
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                try {
                    randomFile.seek(lastTimeFileSize);
                    String tmp = "";
                    while ((tmp = randomFile.readLine()) != null) {
                        String log=new String(tmp.getBytes("ISO8859-1"));
                        LoggerQueue.getInstance().pushFileLog(log);

                    }
                    lastTimeFileSize = randomFile.length();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}