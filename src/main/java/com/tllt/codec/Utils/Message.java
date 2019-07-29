package com.tllt.codec.Utils;

import io.netty.buffer.ByteBuf;

import java.io.Serializable;

public class Message implements Serializable {
    private int length;//总长度
    private ByteBuf data;


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ByteBuf getData() {
        return data;
    }

    public void setData(ByteBuf data) {
        this.data = data;
    }


}
