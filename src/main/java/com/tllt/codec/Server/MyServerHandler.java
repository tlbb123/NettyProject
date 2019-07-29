package com.tllt.codec.Server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        String address=ctx.channel().remoteAddress().toString();
        channels.forEach(channel-> {
            if(channel.remoteAddress().toString().equals(address))
                channel.writeAndFlush("自己" + " :" + msg);
            else
                channel.writeAndFlush(address + " :" + msg);
        });
        System.out.println(msg);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"连接");
        channels.add(ctx.channel());
        channels.forEach(channel ->
                channel.writeAndFlush(channel.remoteAddress()+": 上线"));//暂时使用ip代替用户名
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("welcome");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"离开");
    }
}
