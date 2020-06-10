package com.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wangfei
 * @date 2020-06-10 21:29
 */
public class WebCastServer {

    private int port;

    public WebCastServer (int port){
        this.port = port;
    }

    public void start(){
        //两个线程组
        EventLoopGroup boos = new NioEventLoopGroup();//负责处理新客户的连接
        EventLoopGroup worker = new NioEventLoopGroup();//负责处理客户端的网络请求
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();//对我们的服务端的一些行为或者特征做基本的定义。
            bootstrap.group(boos,worker)//绑定线程组
                    .channel(NioServerSocketChannel.class)//通道：采用nio的这种形式。
                    /**
                     * 服务启动后，在网络上面的传过来的数据都会是【010101传递】都会交由服务器端来处理
                     * 经过多道操作把【010101】还原成了聊天的内容
                     */
                    .childHandler(new WebChatServerInitialize())//处理服务端逻辑
                    .option(ChannelOption.SO_BACKLOG,128)//基本设置
                    .option(ChannelOption.SO_KEEPALIVE,true);//保持这个连接
            ChannelFuture future = bootstrap.bind(port).sync();//让我们的bootstreap绑定到这个端口号。
            System.out.println("[系统消息：服务器已经启动！！！]");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boos.shutdownGracefully();//关闭线程
            worker.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        new WebCastServer(8080).start();
    }
}
