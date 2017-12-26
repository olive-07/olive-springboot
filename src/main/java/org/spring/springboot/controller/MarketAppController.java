package org.spring.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.utils.JsonUtil;
import org.spring.springboot.utils.MapUtils;
import org.spring.springboot.utils.Status;
import org.spring.springboot.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用市场相关api定义
 * @author lyun
 * on 25/12/2017
 */
@RestController
@Configuration
public class MarketAppController {

    private static Logger logger = LoggerFactory.getLogger(MarketAppController.class);
    private final static String CHANNEL_ANDROID = "android";
    @Value("${LOW_VERSION_CODE}")
    private String lowVersionCode;
    @Value("${HIGH_VERSION_CODE}")
    private String highVersionCode;
    @Value("${HIGH_VERSION_NAME}")
    private String highVersionName;
    @Value("${MARKET_APP_DOWNLOAD_URL}")
    private String marketAppDownloadUrl;
    @Value("${MARKET_APP_UPDATE_REMARK}")
    private String marketAppUpdateRemark;

    /**
     * 版本控制
     *
     * @param appVersionCode
     * @param channel
     * @return
     */
    @RequestMapping(value = "/market-app/check-version-app", method = RequestMethod.GET)
    public String checkVersionMarketApp(@RequestParam(value = "appVersionCode",  required = true) String appVersionCode, @RequestParam(value = "channel",  required = true) String channel) {

        logger.info("checkVersionMarketApp appVersionCode:{} channel:{}", appVersionCode, channel);
        Map<String, Object> result = MapUtils.getResultMap(Status.SUCCESS.getName(), Status.SUCCESS.getValue());
        Map<String, Object> rmap = new HashMap<>(16);
        rmap.put("type", 0);
        try {
            if(StringUtils.isNotBlank(appVersionCode) && StringUtils.isNotBlank(channel)){

                int tmp = Integer.parseInt(appVersionCode);
                int low = 0;
                int high = 0;
                if(CHANNEL_ANDROID.equals(channel)){

                    if(StringUtils.isInteger(lowVersionCode) && StringUtils.isInteger(highVersionCode)){
                        low = Integer.valueOf(lowVersionCode);
                        high = Integer.valueOf(highVersionCode);
                    }else{
                        MapUtils.setResultMap(result, Status.FAILD.getName(), "后台版本code值配置错误!");
                    }
                }
                if(tmp < low){
                    //强制更新
                    rmap.put("type", 1);
                }else if(low <= tmp && tmp < high){
                    //提醒更新
                    rmap.put("type", 2);
                }
                rmap.put("newVersionName", (highVersionName == null?"": highVersionName));
                rmap.put("url", (marketAppDownloadUrl == null?"": marketAppDownloadUrl));
                rmap.put("descr", (marketAppUpdateRemark == null?"":marketAppUpdateRemark));
                rmap.put("newVersionCode", high);

            }else{
                MapUtils.setResultMap(result, Status.PARAMS_ERROR.getName(), Status.PARAMS_ERROR.getValue());
            }
        } catch (Exception e) {
            logger.error("checkVersionMarketApp======", e);
            MapUtils.setResultMap(result, Status.FAILD.getName(), Status.FAILD.getValue());
        }finally {
            result.put("data", rmap);
        }
        return JsonUtil.beanToJson(result);
    }

}
