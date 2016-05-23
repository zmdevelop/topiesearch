package com.dm.task.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerilaze {

	 /**
     * 序列化（将Java 对象序列化为可存储的二进制）
     * @param object 对象
     * @return
     */
    public byte[] serialize(Object object) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        byteArrayOutputStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return bytes;
    }

    /**
     * 序列化（把字节序列恢复为对象）
     * @param bytes
     * @return Object
     * <p/>
     * 例 ：详见测试
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public Object deSeialize(byte[] bytes) throws Exception {
        ByteArrayInputStream byteArrayOutputStream = null;
        byteArrayOutputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayOutputStream);
        return objectInputStream.readObject();
    }
}
