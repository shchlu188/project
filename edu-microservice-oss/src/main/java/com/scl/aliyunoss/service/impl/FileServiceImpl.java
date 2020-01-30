package com.scl.aliyunoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.PutObjectRequest;
import com.scl.aliyunoss.service.FileService;
import com.scl.aliyunoss.util.ConstantPropertiesUtil;
import com.scl.common.constants.ResultCodeEnum;
import com.scl.common.exception.SclException;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/24
 * Description:
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.FILE_HOST;
        InputStream inputStream = null;
        String uploadUrl = null;
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            //获取上传文件流
            inputStream = multipartFile.getInputStream();

            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");

            //文件名：uuid.扩展名
            String original = multipartFile.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = fileName + fileType;
            String fileUrl = fileHost + "/" + filePath + "/" + newName;

            //文件上传至阿里云
            ossClient.putObject(bucketName, fileUrl, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //获取url地址
            uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileUrl;
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new SclException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new SclException(ResultCodeEnum.UNKNOW_REASON);
        }

        return uploadUrl;
    }
}
