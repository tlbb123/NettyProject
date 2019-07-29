package com.tllt.codec.Client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Scanner;


public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    private  static GlobalEventExecutor executor;

    private static Scanner scanner;


    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("请求连接");
        executor = GlobalEventExecutor.INSTANCE;
        scanner = new Scanner(System.in);
        executor.submit(() -> {
            while (true)
                ctx.writeAndFlush(scanner.nextLine());
        });
    }

}
