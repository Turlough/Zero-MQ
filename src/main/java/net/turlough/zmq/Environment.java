package net.turlough.zmq;

/**
 * Created by Yuriy Aizenberg
 */
//FIXME: Demo, TestServer and release flavors
public class Environment {


    public static final String SERVER_URL_TEST = "https://demo-v5.over-c.net";
    public static final String AVATAR_URL_PART = "content/user-photo/";


    //LOCAL
    public static final String ZMQ_PUSH_LOCAL = "tcp://*:5556";
    public static final String ZMQ_SEND_LOCAL = "tcp://127.0.0.1:5557";
    //TEST
    public static final String ZMQ_EVENTS_PULL_TEST = "tcp://test-v5.over-c.net:5570";
    public static final String ZMQ_REVISION_PUBLISHER_TEST = "tcp://test-v5.over-c.net::5571";
    //DEMO
    public static final String ZMQ_EVENTS_PULL_DEMO = "tcp://demo-v5.over-c.net:5572";
    public static final String ZMQ_REVISION_PUBLISHER_DEMO = "tcp://demo-v5.over-c.net:5573";


}
