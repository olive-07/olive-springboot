package org.spring.springboot.config.oss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 自定义配置文件实体类
 *
 * @author olive
 * @date 2017年12月28日 9:51
 */
@Configuration
@PropertySource("classpath:config/config.yml")
public class OssConfig {

    @Value("${END_POINT}")
    private String endPoint;

    @Value("${ACCESS_KEY_ID}")
    private String accessKeyId;

    @Value("${ACCESS_KEY_SECRET}")
    private String accessKeySecret;

    @Value("${BUCKET_NAME}")
    private String bucketName;

    @Value("${APP_OSS_URL}")
    private String appOssUrl;

    @Value("${APP_KEY}")
    private String appKey;

    @Value("${MASTER_SECRET}")
    private String masterSecret;

    @Value("${TIME_TO_LIVE}")
    private Long timeToLive;

    @Value("${APNS_PRODUCTION}")
    private Boolean apnsProduction;

    @Value("${NOTICE_MSG_URL}")
    private String noticeMsgUrl;

    @Value("${RETRY_NUM}")
    private Integer retryNum;


    public String getEndPoint() {
        return endPoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getAppOssUrl() {
        return appOssUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public Long getTimeToLive() {
        return timeToLive;
    }

    public Boolean getApnsProduction() {
        return apnsProduction;
    }

    public String getNoticeMsgUrl() {
        return noticeMsgUrl;
    }

    public Integer getRetryNum() {
        return retryNum;
    }
}
