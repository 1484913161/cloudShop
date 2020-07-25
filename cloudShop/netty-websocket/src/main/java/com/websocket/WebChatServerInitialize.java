package com.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author wangfei
 * @date 2020-06-10 21:49
 * 
 *  	【处理服务器逻辑】
 */
public class WebChatServerInitialize extends ChannelInitializer<SocketChannel> {

    /**
     *  对客户端连接过来的请求进行判断是不是websocket请求。
     *  initChannel：【初始化隧道】
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        /**
         * 首先要把【010101】还原成http协议，因为websocket协议不是马上就开始的，
         * 他首先是发送了一个握手的请求【http协议】
         */
        //获得这个SocketChannel【socket管道】
        ChannelPipeline pipeline = socketChannel.pipeline();
        /**这个管道要经过多少部处理。第一步
         * 服务端接到的这个请求和发给客户端的相应
         */
        pipeline.addLast(new HttpServerCodec())//将请求和应答消息编码成http协议的消息。
        //第二步
        .addLast(new HttpObjectAggregator(64*1024))//所接受数据的最大值：64K
        //第三步[如果是http协议]
        .addLast(new ChunkedWriteHandler())//负责向客户端发送html页面文件
        .addLast(new HttpRequestHandler("/chat"))
        //第四步【websocket协议】只要发送到服务器最后反斜杠后面是chat就是websocket协议
        .addLast(new WebSocketServerProtocolHandler("/chat"))
        //业务逻辑【websocket】数据的运用【接受的消息是聊天消息】处理消息
        .addLast(new TextWebSocketFrameHander());

    }
}
