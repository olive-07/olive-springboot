package org.spring.springboot.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spring.springboot.config.oss.OssConfig;
import org.spring.springboot.web.service.AliOssService;
import org.spring.springboot.utils.AliOssUtil;
import org.spring.springboot.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * OSS服务实现类
 *
 * @author olive
 * @date 2017年12月28日 17:53
 */

@Service
public class AliOssServiceImpl implements AliOssService {

    private final static Logger logger = LogManager.getLogger(AliOssServiceImpl.class.getName());

    @Autowired
    private OssConfig cssConfig;

    @Override
    public OSSClient getOSSClient() {
        return new OSSClient(cssConfig.getEndPoint(), cssConfig.getAccessKeyId(), cssConfig.getAccessKeySecret());
    }

    @Override
    public Boolean upload(InputStream inputStream, String fileUrl) {
        if (inputStream == null || StringUtils.isBlank(fileUrl)) return false;
        logger.info("上传文件流uploadInputStream[%s]==========>>>>%s", cssConfig.getBucketName(), fileUrl);
        try {
            OSSClient ossClient = getOSSClient();
            PutObjectResult putObjectResult = ossClient.putObject(cssConfig.getBucketName(), fileUrl, inputStream, new ObjectMetadata());
            logger.info("上传返回结果==========>>>>%s", JSON.toJSON(putObjectResult));
            return putObjectResult != null;
        } catch (OSSException ossExp) {
            logger.error("下载文件异常", ossExp);
        } catch (ClientException clientExp) {
            logger.error("创建OSS客户端异常", clientExp);
        }

        return false;

    }

    @Override
    public Boolean downloadFile(String filePath) {
        if (StringUtils.isBlank(filePath)) return false;
        try {
            OSSClient ossClient = getOSSClient();
            File file = new File(filePath);
            // 下载object到文件
            ObjectMetadata objectMetadata = ossClient.getObject(new GetObjectRequest(cssConfig.getBucketName(), filePath), file);
            logger.info("下载返回结果==========>>>>%s", JSON.toJSON(objectMetadata));
            return objectMetadata != null;
        } catch (OSSException ossExp) {
            logger.error("下载文件异常", ossExp);
        } catch (ClientException clientExp) {
            logger.error("创建OSS客户端异常", clientExp);
        }

        return false;
    }

    @Override
    public Boolean deleteFile(String key) {
        if (StringUtils.isBlank(key)) return false;
        logger.info("删除文件 deleteFile 桶名为%s, key值为%s", cssConfig.getBucketName(), key);
        try {
            OSSClient ossClient = getOSSClient();
            // 删除Object
            ossClient.deleteObject(cssConfig.getBucketName(), key);
        } catch (OSSException ossExp) {
            logger.error("删除文件异常", ossExp);
        } catch (ClientException clientExp) {
            logger.error("创建OSS客户端异常", clientExp);
        }
        return false;
    }

    @Override
    public String uploadObject2OSS(File file, String bucketName, String diskName) {
        if (file == null || StringUtils.isBlank(bucketName) || StringUtils.isBlank(diskName)) return "参数为空";
        try(InputStream inputStream = new FileInputStream(file)) {
            String fileName = file.getName();
            Long fileSize = file.length();
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(AliOssUtil.getContentType(fileName));
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            // 创建OSSClient实例
            OSSClient ossClient = getOSSClient();
            // 上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName, diskName + fileName, inputStream, metadata);
            // 解析结果
            return putResult.getETag();
        } catch (OSSException ossExp) {
            logger.error("上传阿里云OSS服务器异常", ossExp);
        } catch (ClientException clientExp) {
            logger.error("创建OSS客户端异常", clientExp);
        } catch (IOException e) {
            logger.error("文件流读取异常",e);
        }
        return "文件上传异常";
    }
}
