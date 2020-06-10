package com.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author wangfei
 * @date 2020-06-10 23:13
 */
public class TextWebSocketFrameHander extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //定义一个通道【这里面包含了所有连接的客户】
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /***
     *  客户端连接时候自动执行那么就连接上来的客户记录下来
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for(Channel ch : channels  ){
            if(ch != channel){
                ch.writeAndFlush("[欢迎]" + channel.remoteAddress() + "进入聊天室");
            }
        }
        //新连接的进行添加到通道
        channels.add(channel);
    }

    /**
     * 客户端退出自动执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for(Channel ch : channels){
            if(ch != channel){
                ch.writeAndFlush("[再见]" + channel.remoteAddress() + "离开聊天室");
            }
        }
        //新连接的进行添加到通道
        channels.remove(channel);
    }

    /**
     * 当客户端发送过来消息执行
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("'11111111111111");
        Channel channel = ctx.channel();//获取发消息人的连接通道
        for(Channel channel1 : channels){
            if(channel1 != channel){
                channel1.writeAndFlush(new TextWebSocketFrame("用户" + channel1.remoteAddress() + "说:" + msg.text()));
            }else{
                channel1.writeAndFlush(new TextWebSocketFrame("我说:" + msg.text()));
            }
        }
    }
}
