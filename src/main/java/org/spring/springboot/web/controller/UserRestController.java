package org.spring.springboot.web.controller;

import org.spring.springboot.config.jediscluster.service.JedisClusterService;
import org.spring.springboot.config.redis.service.RedisService;
import org.spring.springboot.web.domain.User;
import org.spring.springboot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 * @author lyun
 * on 07/07/2017.
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private JedisClusterService jedisClusterService;

    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public User findByName(@RequestParam(value = "userName", required = true) String userName) {
        return userService.findByName(userName);
    }

    /**
     * 单机redis测试
     *
     * @return
     */
    @RequestMapping(value = "/api/redis/test", method = RequestMethod.GET)
    public String redisTest() {

        redisService.add("redis_test","112312312");
        return (String)redisService.get("redis_test");
    }

    /**
     * 集群redis测试
     *
     * @return
     */
    @RequestMapping(value = "/api/jedisCluster/test", method = RequestMethod.GET)
    public String jedisClusterTest() {

        jedisClusterService.set("", "jedisCluster_test", "1231223112312312");
        return jedisClusterService.get("", "jedisCluster_test");
    }
}
