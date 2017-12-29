package org.spring.springboot.web.service;

import org.spring.springboot.web.domain.User;

/**
 * 用户业务接口层
 * @author olive
 * on 07/07/2017.
 */
public interface UserService {

    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @param userName
     * @return
     */
    User findByName(String userName);
}
