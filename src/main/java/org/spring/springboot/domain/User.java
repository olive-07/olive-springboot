package org.spring.springboot.domain;

/**
 * 用户实体类
 * @author olive
 * on 07/07/2017.
 */
public class User {

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 城市名称
     */
    private String userName;


    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
