package org.spring.springboot.web.service.impl;

import org.spring.springboot.web.dao.cluster.CityDao;
import org.spring.springboot.web.dao.master.UserDao;
import org.spring.springboot.web.domain.City;
import org.spring.springboot.web.domain.User;
import org.spring.springboot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现层
 * @author olive
 * on 07/07/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CityDao cityDao;

    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        City city = cityDao.findByName("温岭市");
        user.setCity(city);
        return user;
    }
}
