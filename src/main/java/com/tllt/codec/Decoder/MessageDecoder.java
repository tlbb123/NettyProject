package com.tllt.codec.Decoder;

import com.tllt.codec.Utils.MyCodecHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

enum MessageState {
    LENGTH, DATA

}

public class MessageDecoder extends ReplayingDecoder<MessageState> {

    private int length;

    public MessageDecoder() {
        super(MessageState.LENGTH);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        switch (state()) {
            case LENGTH:
                length = in.readInt();
                checkpoint(MessageState.DATA);
                break;
            case DATA:
                ByteBuf data = in.readBytes(length);
                checkpoint(MessageState.LENGTH);
                out.add(data);
                break;
            default:
                throw new Error("Shouldn't reach here.");

        }
    }
}
