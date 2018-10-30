 package com.dovepay.test.server.b.bean;

import java.util.Date;

/**
 * @author zhaoyh
 * @date 2018/10/27
 */
public class B {
    private String version;
    private int id;
    private String name;
    private String description;
    private Date createTime;
    private String environment;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    /**
     * @return the environment
     */
    public String getEnvironment() {
        return environment;
    }
    /**
     * @param environment the environment to set
     */
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
    
    @Override
    public String toString() {
        return "B [version=" + version + ", id=" + id + ", name=" + name + ", description=" + description
            + ", createTime=" + createTime + ", environment=" + environment + "]";
    }
    
}
