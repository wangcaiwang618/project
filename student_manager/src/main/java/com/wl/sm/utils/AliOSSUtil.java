package com.wl.sm.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;

import java.io.File;
import java.util.UUID;

/**
 * @ClassName AliOSSUtil
 * @Description 阿里云oss上传工具类
 * @Author WL
 * @Date 2020/11/20
 **/
public class AliOSSUtil {
    public static String ossUpload(File file) {
        String bucketDomain = "https://wl-oss-niit-soft.oss-cn-hangzhou.aliyuncs.com/";
        //Endpoint以杭州为例
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAI4G3hVJHTuvdVkfAhR9MJ";
        String  accessKeySecret = "Ql1ZmOqD2V9YRji8cq3PTctOnmbfrB";
        //Bucket名称
        String bucketName = "wl-oss-niit-soft";
        //目录名称
        String fileDir = "img/";
        //获得本地文件的文件名
        String fileName = file.getName();
        //上传后文件名生成
        String fileKey = UUID.randomUUID().toString() + fileName.substring(fileName.indexOf("."));
        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //上传file到BucketName的FileDir目录下，用fileKey作为新的文件名存储
        ossClient.putObject(bucketName, fileDir + fileKey, file);
        //关闭OSSClient实例
        ossClient.shutdown();
        //返回URL组成：域名+目录+fileKey
        return bucketDomain + fileDir + fileKey;
    }

    public static void main(String[] args) {
        File file = new File("E:\\img\\me.jpg");
        String url = ossUpload(file);
        System.out.println(url);
    }
}
