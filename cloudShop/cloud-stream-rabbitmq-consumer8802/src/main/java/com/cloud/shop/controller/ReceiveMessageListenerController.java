package com.cloud.shop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author wangfei
 * @date 2020-04-19 19:13
 * 【消费者:Sink】
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)//监听sink  input这个输入源
    public void input(Message<String> message){
        System.out.println("******消费则1号****--------->接受到得消息"+message.getPayload()+"\t  port:"+serverPort);
    }
}
