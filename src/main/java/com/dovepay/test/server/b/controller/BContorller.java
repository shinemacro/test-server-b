 package com.dovepay.test.server.b.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dovepay.test.server.b.bean.B;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author zhaoyh
 * @date 2018/10/27
 */
@RestController
@RequestMapping(value="/b")
@Api(value = "b", description = "b管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class BContorller {
    
    @Autowired
    private Environment env; 
    
    @RequestMapping(method=RequestMethod.GET)
    @ApiOperation(value="查询所有b接口", notes="返回所有b")
    public String getAllB(){
        System.out.println("getAllB run...");
        return "all b Hello World!";
    }
    
    @ApiOperation(value="b查询接口", notes="根据ID返回b")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Object getB(@PathVariable(value = "id") Integer id){
        B b = new B();
        b.setId(id);
        b.setCreateTime(new Date());
        b.setDescription("server B");
        b.setVersion("0.0.1");
        b.setName("testB");
        b.setEnvironment(env.getProperty("b.env"));
        System.out.println("getB:"+b);
        return b;
    }
}
