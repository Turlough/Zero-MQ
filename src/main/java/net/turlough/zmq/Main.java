package net.turlough.zmq;

/**
 * Created by turlough on 21/08/16.
 */
public class Main {

public static void main(String... args){
    ZmqPublisher publisher = new ZmqPublisher(ZmqPublisher.ADDRESS);
    ZmqSubsciber subscriber = new ZmqSubsciber(ZmqPublisher.ADDRESS);
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
}
