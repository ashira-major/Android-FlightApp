package com.project.fgapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Model {
    static Executor executor = Executors.newSingleThreadExecutor();
    private Socket fg = null;
    private PrintWriter out = null;
    private int flag = 0;

    public void setStart(String ip, String port) throws IOException {
        executor.execute(()->{
            try {
                this.fg = new Socket(ip, Integer.parseInt(port));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.out = new PrintWriter(fg.getOutputStream(), true);

            } catch (IOException e) {

                e.printStackTrace();
            }
            flag = 1;

        });

    }



    public void setFlightVar(String s, float v) {
        if (flag == 1) {
            executor.execute(()->{
                out.print(s + v + "\r\n");
                out.flush();
            });
        }
    }
}
