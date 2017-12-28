package org.spring.springboot.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spring.springboot.config.oss.OssConfig;
import org.spring.springboot.constant.ConstantCommon;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.AliOssService;
import org.spring.springboot.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
/**
 * oss 上传
 * @author lyun
 * on 28/12/2017
 */
@RestController
@RequestMapping("/upload/")
public class FileUploadController {

    private final static Logger logger = LogManager.getLogger(FileUploadController.class.getName());

    @Autowired
    private AliOssService aliOssService;

    @Autowired
    private OssConfig cssConfig;

    /**
     * img类型图片文件上传
     * @param file 文件对象
     * @return 返回处理结果
     */
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "不能上传空文件";
        }
        // 如果文件大小大于限制
        if (file.getSize() > ConstantCommon.FILE_SIZE) {
            return "图片过大,请选择小于1M的图片";
        }
        // 文件名
        final String fileName = file.getOriginalFilename();
        // 获取文件类型
        String ext = FilenameUtils.getExtension(fileName).toLowerCase();
        if (!Arrays.asList(AliOssUtil.CONTENT_TYPE_MAP.get("image").split(",")).contains(ext)) {
            return "上传图片格式不符合规范";
        }

        // 获取文件位置
        String filePath = ConstantCommon.FILEPATH.concat(AliOssUtil.randomPathname(ext));
        logger.info("文件filePath==>" , filePath);
        try {
            // 上传OSS
            Boolean ossResult = aliOssService.upload(file.getInputStream(), filePath);
            logger.info("调用文件上传返回ossResult:" + ossResult);
            if (ossResult) return cssConfig.getAppOssUrl() + "/" + filePath;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "上传图片发生异常";
        }
        // 返回json
        return "上传图片失败，请重新上传！";
    }

}
