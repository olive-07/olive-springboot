package org.spring.springboot.web.service;

import com.aliyun.oss.OSSClient;

import java.io.File;
import java.io.InputStream;

/**
 * OSS服务接口
 *
 * @author whh
 * @date 2017年11月07日 17:05
 */


public interface AliOssService {

    /**
     * @description 获取OSS客户端对象
     * @date 2017年11月07日 17:07:40
     * @author whh
     */
    OSSClient getOSSClient();

    /**
     * @description 上传文件
     * @param inputStream 数据流对象
     * @param fileUrl 文件路径
     * @return 返回处理结果
     * @date 2017年11月7日17:19:56
     * @author whh
     */
    Boolean upload(InputStream inputStream, String fileUrl);

    /**
     *  下载文件
     * @param filePath 文件路劲
     * @return 返回处理结果
     * @date 2017年11月7日17:20:20
     * @author whh
     */
    Boolean downloadFile(String filePath);

    /**
     * 删除文件
     * @param key 文件KEY
     * @return 返回处理结果
     * @date 2017年11月7日17:21:20
     * @author whh
     */
    Boolean deleteFile(String key);

    /**
     * 向阿里云的OSS存储中存储文件 --file也可以用InputStream替代
     * @param file 上传文件
     * @param bucketName bucket名称
     * @param diskName 上传文件的目录 --bucket下文件的路径
     * @return 唯一MD5数字签名
     * @date 2017年11月7日17:22:46
     * @author whh
     */
    String uploadObject2OSS(File file, String bucketName, String diskName);

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 返回文件的contentType
     * @date 2017年11月7日17:23:41
     * @author whh
     */
//    String getContentType(String fileName);
}
