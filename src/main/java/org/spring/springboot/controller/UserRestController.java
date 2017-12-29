package org.spring.springboot.controller;

import org.spring.springboot.config.redis.service.RedisService;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
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
    public String findByName() {

        redisService.add("redis_test","112312312");
        return (String)redisService.get("redis_test");
    }
}
