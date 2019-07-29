package com.tllt.codec.Encoder;

import com.tllt.codec.Utils.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 这是最后一个编码器，需要写入到ByteBuf中
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getData());
    }
}