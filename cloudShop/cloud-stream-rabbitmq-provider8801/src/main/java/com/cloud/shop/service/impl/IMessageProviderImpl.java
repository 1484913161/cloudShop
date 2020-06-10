package com.cloud.shop.service.impl;

import com.cloud.shop.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import java.util.UUID;

import javax.annotation.Resource;

/**
 * @author wangfei
 * @date 2020-04-19 16:48
 *
 * 【生产者：Source】
 */
@EnableBinding(Source.class)//定义消息推送管道
public class IMessageProviderImpl implements IMessageProvider {

    //消息发送管道
	@Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        //通过outPut通道发送消息
        System.out.println("*******serial********"+serial);
        return null;
    }
}
