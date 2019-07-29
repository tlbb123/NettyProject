package com.tllt.codec.Encoder;

import com.tllt.codec.Utils.MyCodecHelper;
import com.tllt.codec.Utils.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class StringToMessageEncoder extends MessageToMessageEncoder<String> {

    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        Message message = new Message();

        ByteBuf byteBuf= Unpooled.copiedBuffer(msg.toCharArray(), MyCodecHelper.charset);
        message.setLength(byteBuf.readableBytes());
        message.setData(byteBuf);
        out.add(message);
    }

}
