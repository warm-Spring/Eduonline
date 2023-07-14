package com.xidian.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xidian.oss.service.FileService;
import com.xidian.oss.util.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements FileService {
    //上传头像到oss
    @Override
    public String upload(MultipartFile file) {
        //工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        System.out.println("endpoint:"+endpoint);
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        System.out.println(accessKeyId);
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        System.out.println(accessKeySecret);
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        System.out.println(bucketName);

        try{
            //创建OSS实例
            OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);

            //获取文件名称
            String fileName = file.getOriginalFilename();

            //1.在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName = uuid+fileName;


            //2.把文件按照日期进行分类
            //SimpleDateFormat
            //2023/4/24/01.jpg
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath+"/"+fileName;
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //调用oss方法实现上传
            //第一个参数 Bucket名称
            //第二个参数 上传到oss文件路径和文件名称     /aa/bb/1.jpg
            //第三个参数 上传文件输入流

            ossClient.putObject(bucketName,fileName,inputStream );

            //关闭OSSClient
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //https://edu-xd-fc.oss-cn-qingdao.aliyuncs.com/0.jpg
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;

            return url;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
