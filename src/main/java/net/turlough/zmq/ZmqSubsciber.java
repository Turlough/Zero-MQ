package net.turlough.zmq;


import org.zeromq.ZMQ;

import java.util.concurrent.TimeUnit;

/**
 * Created by turloughcowman on 15/08/2016.
 */

public class ZmqSubsciber extends Thread {

    public final static String TAG = ZmqSubsciber.class.getSimpleName();

    public static void main(String... args){
        ZmqSubsciber subsciber = new ZmqSubsciber(ZmqPublisher.ADDRESS);
//        ZmqSubsciber subsciber = new ZmqSubsciber("tcp://test-v5.over-c.net:5571");
        subsciber.start();
    }

    private ZMQ.Context context = ZMQ.context(1);
    private ZMQ.Socket socket = context.socket(ZMQ.SUB);

    private final String address;


    public ZmqSubsciber(String address) {

        this.address = address;
        setName(getClass().getSimpleName());
    }

    @Override
    public void run() {

        socket.connect(address);
        socket.subscribe(new byte[]{});
        sleep(100);

        //allow time for the connection to establish before listening
        sleep(100);
        System.out.println("Subscriber connected: "+ address);

        while (!isInterrupted()) {
            String string = socket.recvStr(0).trim();

            System.out.println("Subscriber received: " + string);
        }


        //close
        socket.close();
        context.term();
        System.out.println("Subscriber closed");


    }


    private void sleep(int millis) {

        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
