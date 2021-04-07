package com.wzy.entiy;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 作者：王子瑜
 * 时间：2021/3/19 10:04
 * 描述：
 */
public class User {

    private String name;
    private Integer age;
    private Date birthday;
    private Timestamp timestamp;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
