package com.gome.client.beanstalk.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerlizaUtils {
    public static Object getObjectFromBytes(byte[] objBytes) throws Exception {
    if (objBytes == null || objBytes.length == 0) {
        return null;
    }
    ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
    ObjectInputStream ois = new ObjectInputStream(bi);
    return ois.readObject();
    }
 
    public static byte[] getBytesFromObject(Serializable obj) throws Exception {
    if (obj == null) {
        return null;
    }
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bo);
    oos.writeObject(obj);
    return bo.toByteArray();
    }
}
