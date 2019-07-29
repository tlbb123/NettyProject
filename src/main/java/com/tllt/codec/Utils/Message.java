package com.tllt.codec.Utils;

import java.io.Serializable;

public class Message implements Serializable {
    private int length;//总长度
    private byte[] data;


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


}
