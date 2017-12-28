package org.spring.springboot.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * oss 上传工具
 * @author lyun
 * on 28/12/2017
 */
public class AliOssUtil {

    public static final Map<String, String> CONTENT_TYPE_MAP = new HashMap<String, String>();

    public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static {
        CONTENT_TYPE_MAP.put("image", "gif,jpg,jpeg,png,bmp");
        CONTENT_TYPE_MAP.put("flash", "swf,flv");
        CONTENT_TYPE_MAP.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        CONTENT_TYPE_MAP.put("file", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,dwg,pdf");
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        switch (fileExtension) {
            case "bmp":
                return "image/bmp";
            case "gif":
                return "image/gif";
            case "jpeg":
                return "image/jpeg";
            case "jpg":
                return "image/jpeg";
            case "png":
                return "image/jpeg";
            case "html":
                return "text/html";
            case "txt":
                return "text/plain";
            case "vsd":
                return "application/vnd.visio";
            case "ppt":
                return "application/vnd.ms-powerpoint";
            case "pptx":
                return "application/vnd.ms-powerpoint";
            case "doc":
                return "application/msword";
            case "docx":
                return "application/msword";
            case "xml":
                return "text/xml";
            default:
                return "text/html";
        }
    }

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * @param pattern   日期格式字符串
     * @param extension 文件类型
     * @return 返回文件名
     */
    public static String randomPathname(String pattern, String extension) {
        StringBuilder filename = new StringBuilder();
        DateFormat df = new SimpleDateFormat(pattern);
        filename.append(df.format(new Date()));
        filename.append(generateString(10));
        if (StringUtils.isNotBlank(extension)) {
            filename.append(".").append(extension.toLowerCase());
        }
        return filename.toString();
    }

    public static String randomPathname(String extension) {
        return randomPathname("/yyyyMMdd/yyyyMMddHHmmss_", extension);
    }

}
