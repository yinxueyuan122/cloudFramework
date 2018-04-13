package com.cn.cloud.redis.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * 
 * @ClassName   : SerializeUtil.java
 * @Description : 
 * @author Yin Xueyuan
 * @since 2018年4月13日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2018年4月13日        Yin Xueyuan           fisrt create
 * </pre>
 */
public class SerializeUtil {
	
	private static final String DEFAULT_ENCODING = "UTF-8";
	 
    private static final ThreadLocal<Kryo> kryoLocal = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
 
            return kryo;
        }
    };
 
    /**
     * 获得当前线程的 Kryo 实例
     *
     * @return 当前线程的 Kryo 实例
     */
    public static Kryo getInstance() {
        return kryoLocal.get();
    }
 
    //-----------------------------------------------
    //          序列化/反序列化对象，及类型信息
    //          序列化的结果里，包含类型的信息
    //          反序列化时不再需要提供类型
    //-----------------------------------------------
 
    /**
     * 将对象【及类型】序列化为字节数组
     *
     * @param obj 任意对象
     * @param <T> 对象的类型
     * @return 序列化后的字节数组
     */
    public static <T> byte[] writeToByteArray(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
 
        Kryo kryo = getInstance();
        kryo.writeClassAndObject(output, obj);
        output.flush();
 
        return byteArrayOutputStream.toByteArray();
    }
 
    /**
     * 将对象【及类型】序列化为 String
     * 利用了 Base64 编码
     *
     * @param obj 任意对象
     * @param <T> 对象的类型
     * @return 序列化后的字符串
     */
    public static <T> String writeToString(T obj) {
        try {
            return new String(Base64.encodeBase64(writeToByteArray(obj)), DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
 
    /**
     * 将字节数组反序列化为原对象
     *
     * @param byteArray writeToByteArray 方法序列化后的字节数组
     * @param <T>       原对象的类型
     * @return 原对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T readFromByteArray(byte[] byteArray) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        Input input = new Input(byteArrayInputStream);
 
        Kryo kryo = getInstance();
        return (T) kryo.readClassAndObject(input);
    }
 
    /**
     * 将 String 反序列化为原对象
     * 利用了 Base64 编码
     *
     * @param str writeToString 方法序列化后的字符串
     * @param <T> 原对象的类型
     * @return 原对象
     */
    public static <T> T readFromString(String str) {
        try {
            return readFromByteArray(Base64.decodeBase64(str.getBytes(DEFAULT_ENCODING)));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
 
    //-----------------------------------------------
    //          只序列化/反序列化对象
    //          序列化的结果里，不包含类型的信息
    //-----------------------------------------------
 
    /**
     * 将对象序列化为字节数组
     *
     * @param obj 任意对象
     * @param <T> 对象的类型
     * @return 序列化后的字节数组
     */
    public static <T> byte[] writeObjectToByteArray(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
 
        Kryo kryo = getInstance();
        kryo.writeObject(output, obj);
        output.flush();
 
        return byteArrayOutputStream.toByteArray();
    }
    
 
    /**
     * 将对象序列化为 String
     * 利用了 Base64 编码
     *
     * @param obj 任意对象
     * @param <T> 对象的类型
     * @return 序列化后的字符串
     */
    public static <T> String writeObjectToString(T obj) {
        try {
            return new String(Base64.encodeBase64(writeObjectToByteArray(obj)), DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
 
    /**
     * 将字节数组反序列化为原对象
     *
     * @param byteArray writeToByteArray 方法序列化后的字节数组
     * @param clazz     原对象的 Class
     * @param <T>       原对象的类型
     * @return 原对象
     */
    public static <T> T readObjectFromByteArray(byte[] byteArray, Class<T> clazz) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        Input input = new Input(byteArrayInputStream);
 
        Kryo kryo = getInstance();
        return kryo.readObject(input, clazz);
    }
 
    /**
     * 将 String 反序列化为原对象
     * 利用了 Base64 编码
     *
     * @param str   writeToString 方法序列化后的字符串
     * @param clazz 原对象的 Class
     * @param <T>   原对象的类型
     * @return 原对象
     */
    public static <T> T readObjectFromString(String str, Class<T> clazz) {
        try {
            return readObjectFromByteArray(Base64.decodeBase64(str.getBytes(DEFAULT_ENCODING)), clazz);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

}
}
