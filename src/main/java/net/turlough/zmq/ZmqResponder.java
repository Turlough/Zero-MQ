package net.turlough.zmq;

import org.zeromq.ZMQ;


public class ZmqResponder implements Runnable {

    String address;
    ZMQ.Context context = ZMQ.context(1);
    ZMQ.Socket socket = context.socket(ZMQ.REP);
 
    public ZmqResponder(String address) {
        this.address = address;
    }
 
    @Override
    public void run() {

        socket.bind(address);
        System.out.println("Server bound to " + address);
        while(!Thread.currentThread().isInterrupted()) {
            byte[] msg = socket.recv(0);
            System.out.println("Server received: " + new String(msg));
            socket.send("OK", 0);
        }

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

    public void send(String msg){
        socket.send(msg, 0);
    }
}
