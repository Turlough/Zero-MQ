package net.turlough.zmq;

import java.util.concurrent.TimeUnit;

/**
 * Created by turlough on 21/08/16.
 */
public class Interrupter extends Thread {

    int delay;
    Thread thread;

    public Interrupter(int delay, Thread thread) {
        this.delay = delay;
        this.thread = thread;
    }

    public void run(){
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
