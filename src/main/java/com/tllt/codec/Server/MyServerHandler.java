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
        channels.forEach(channel->{
            channel.writeAndFlush(channel.remoteAddress()+" :"+msg);
        });
        System.out.println(msg);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"连接");
        channels.add(ctx.channel());
        System.out.println(channels.size());
        channels.forEach(channel ->
                ctx.writeAndFlush(channel.remoteAddress()+"已经上线"));//暂时使用ip代替用户名
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"离开");
    }
}
