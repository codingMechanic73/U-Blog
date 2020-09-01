package com.upgrad.ublog.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class LogWriter extends Thread {

    private final String logMessage;
    private final String path;

    public LogWriter(String logMessage, String path) {
        this.logMessage = logMessage;
        this.path = path;
    }


    @Override
    public void run() {
        try {
            writeLog(this.logMessage, this.path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void writeLog(String logMessage, String path) throws IOException {

            FileWriter fw = new FileWriter(path + "/logs.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(logMessage);
            bw.newLine();
            bw.close();
    }
}
