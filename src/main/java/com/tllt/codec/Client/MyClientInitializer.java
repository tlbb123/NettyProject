package com.tllt.codec.Client;

import com.tllt.codec.Decoder.MessageDecoder;
import com.tllt.codec.Decoder.MessageToStringDecoder;
import com.tllt.codec.Encoder.MessageEncoder;
import com.tllt.codec.Encoder.StringToMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MessageDecoder()).
                addLast(new MessageToStringDecoder()).
                addLast(new MessageEncoder()).
                addLast(new StringToMessageEncoder()).
                addLast(new MyClientHandler());
    }
}
