package com.tllt.codec.Encoder;

import com.tllt.codec.Utils.MyCodecHelper;
import com.tllt.codec.Utils.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;

public class StringToMessageEncoder extends MessageToMessageEncoder<String> {

    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        Message message = new Message();
        byte[] bytes = MyCodecHelper.charset.encode(msg).array();
        message.setData(bytes);
        message.setLength(bytes.length);
        out.add(message);
    }
}
