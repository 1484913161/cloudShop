package com.websocket;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedNioFile;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author wangfei
 * @date 2020-06-10 22:18
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String chatUrl;

    private static File indexFile;

    public HttpRequestHandler(String chatUrl) {
        this.chatUrl = chatUrl;
    }

    static {
        //获取实际地址
        try {
            URL location = HttpRequestHandler.class.getProtectionDomain().getCodeSource().getLocation();
            //String path = location.toURI() + "index.html";
            String path = "E:/workspace/Ider/cloudShop/cloudShop/cloudShop/netty-websocket/src/main/resources/index.html";
            //String path = "D:/java/workspase/idea/cloudshop/cloudShop/netty-websocket/src/main/resources/index.html";
            indexFile = new File(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    /**
     * 【读取客户端所发过来的请求，并向客户端发送响应的。】
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        //获取uri
        System.out.println(request.getUri());
        //判断uri是否相等如果相等就是websocket请求
        if(chatUrl.equalsIgnoreCase(request.getUri())){
            System.out.println("请求是WebSocket请求！！！");
            //下一道工序处理
            ctx.fireChannelRead(request.retain());
        }else{
            System.out.println("请求是Http请求！！！，则需要获取index.html!!!");
            //如果是http协议进行发送index.html页面
            /***
             * if请求是100那么进入下一步
             */
            if(HttpHeaders.is100ContinueExpected(request)){
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.CONTINUE);
                ctx.writeAndFlush(response);
            }
            //读取默认的index.html页面[r:读取]
            RandomAccessFile file =  new RandomAccessFile(indexFile,"r");
            //设置响应头
            HttpResponse response = new DefaultHttpResponse(request.getProtocolVersion(),HttpResponseStatus.OK);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE,"text/html;charset=UTF-8");
            boolean keepAlive = HttpHeaders.isKeepAlive(request);
            if(keepAlive){
                response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,file.length());
                response.headers().set(HttpHeaders.Names.CONNECTION,HttpHeaders.Values.KEEP_ALIVE);
            }

            //把相应写到客户端
            ctx.write(response);
            //读取我们的文件[将html写道客户端]
            ctx.write(new ChunkedNioFile(file.getChannel()));
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if(!keepAlive){
                future.addListener(ChannelFutureListener.CLOSE);
            }
            //关闭
            file.close();
        }
    }
}
