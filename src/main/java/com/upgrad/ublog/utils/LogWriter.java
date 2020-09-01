package com.upgrad.ublog.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * TODO: 8.1. Implement writeLog() method with the following method signature.
 *  public static void writeLog(String logMessage, String path) throws IOException
 *  This method should append the log message to the log file that is stored at the
 *  Input path.
 */

public class LogWriter extends Thread{

    private String logMessage;
    private String path;

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

        try {
            FileWriter fw = new FileWriter(path + "/logs.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(logMessage);
            bw.newLine();
            bw.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
