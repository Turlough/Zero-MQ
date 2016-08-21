package net.turlough.zmq;

import org.zeromq.ZMQ;

import java.util.concurrent.TimeUnit;

/**
 * Created by turloughcowman on 16/08/2016.
 */

public class ZmqPublisher extends Thread{

    public static final String ADDRESS = "tcp://*:5556";
    String address;
    ZMQ.Context context = ZMQ.context(1);
    ZMQ.Socket socket = context.socket(ZMQ.PUB);

    public static void main(String... args){

        ZmqPublisher publisher = new ZmqPublisher(ADDRESS);
        ZmqSubsciber subscriber = new ZmqSubsciber(ADDRESS);
        Interrupter pInterrupter = new Interrupter(2, publisher);
        Interrupter sInterrupter = new Interrupter(6, subscriber);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("W: interrupt received, killing subscriber…");
                subscriber.stop();
                try {
                    subscriber.interrupt();
                    subscriber.join();
                } catch (InterruptedException e) {
                }
            }
        });


        publisher.start();
        subscriber.start();
//        pInterrupter.start();
//        sInterrupter.start();
    }



    public ZmqPublisher(String address) {
        this.address = address;
        setName(getClass().getSimpleName());
    }

    public void run(){
        open();
        for (int i = 0; i < 5; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                break;
            }
            publish("Tick-" + i);
            if(isInterrupted()) break;
        }
        publish("Tock");
        close();
    }

    public void open(){
        socket.bind(address);
        System.out.println("Server bound to " + address);

    }

    public void close(){

        socket.close();
        context.term();
        System.out.println("Server closed");

    }

    public void publish(String msg){
        boolean sent = socket.send(msg);
        System.out.println("Server pushed: " + msg + ", success = " + sent);
    }

    private void sleep(int millis){

        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
