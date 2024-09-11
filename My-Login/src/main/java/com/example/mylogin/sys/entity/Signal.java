package com.example.mylogin.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;

import java.util.Date;
import java.io.Serializable;

/**
 * (Signal)实体类
 *
 * @author makejava
 * @since 2024-04-26 14:01:06
 */
@Getter
@TableName("x_signal")
public class Signal implements Serializable {
    private static final long serialVersionUID = 646083053879328208L;

    private Integer id;

    private String name;

    private String shortname;

    private String user;

    private Date date;

    private String status;

    private String url;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortname() {
        return shortname;
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Signal{" +
                "id = " + id +
                ", name = " + name +
                ", shortname = " + shortname +
                ", user = " + user +
                ", date = " + date +
                ", status = " + status +
                ", url = " + url +
                "}";
    }
}

