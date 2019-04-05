 package com.dovepay.test.server.b.controller;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping(value="/")
@Api(value = "b", description = "b管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class BContorller {
    
    @Autowired
    private Environment env; 
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    @ApiOperation(value="test-server-b服务启动接口", notes="返回服务信息")
    public Object index(){
        System.out.println("test-server-b run...");
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("serverVersion", "v3");
        result.put("serverName", "test-server-b");
        result.put("spring.profiles.active", env.getProperty("spring.profiles.active"));
        result.put("env", env.getProperty("b.env"));
        try {
            result.put("localName", InetAddress.getLocalHost().getHostName());
            result.put("localIp", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
             e.printStackTrace();
        }
        result.put("ipList", getLocalIPList());
        
        // Get JVM's thread system bean
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        // Get start time
        long startTime = bean.getStartTime();
        // Get start Date
        Date startDate = new Date(startTime);
        result.put("jvmStartTime", new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(startDate));
        
        System.out.println("======通过ManagementFactory.getMemoryMXBean()来获取相关系统状态======");
        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();   
        MemoryUsage usage = memorymbean.getHeapMemoryUsage();   
        System.out.println("INIT HEAP: " + usage.getInit()/1024);   
        System.out.println("MAX HEAP: " + usage.getMax()/1024);   
        System.out.println("USE HEAP: " + usage.getUsed()/1024);   
        System.out.println("\nFull Information:");   
        System.out.println("Heap Memory Usage: " + memorymbean.getHeapMemoryUsage());   
        System.out.println("Non-Heap Memory Usage: " + memorymbean.getNonHeapMemoryUsage());  
        result.put("INIT HEAP", usage.getInit()/1024);
        result.put("MAX HEAP: ", usage.getMax()/1024);
        result.put("USE HEAP: ", usage.getUsed()/1024);
        result.put("Non-Heap Memory Usage: ", memorymbean.getNonHeapMemoryUsage());

        System.out.println("======通过Runtime.getRuntime()来获取相关系统状态======");
        Runtime runtime = Runtime.getRuntime();
        int totalMemory = (int)runtime.totalMemory()/1024;//Java 虚拟机中的内存总量,以字节为单位  
        int freeMemory = (int)runtime.freeMemory()/1024;//Java 虚拟机中的空闲内存量  
        int maxMemory = (int)Runtime.getRuntime().maxMemory()/1024;
        System.out.println("总的内存量 totalMemory is "+totalMemory);         
        System.out.println("空闲内存量 freeMemory is " + freeMemory);          
        System.out.println("最大内存量maxMemory is " + maxMemory);  
        result.put("总的内存量 totalMemory", totalMemory);
        result.put("空闲内存量 freeMemory", freeMemory);
        result.put("最大内存量maxMemory is ", maxMemory); 
        
        return result;
    }
    
    @RequestMapping(value="/b", method=RequestMethod.GET)
    @ApiOperation(value="查询所有b接口", notes="返回所有b")
    public String getAllB(){
        System.out.println("getAllB run...");
        return "all b Hello World!";
    }
    
    @ApiOperation(value="b查询接口", notes="根据ID返回b")
    @RequestMapping(value="/b/{id}", method=RequestMethod.GET)
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
    
    public static List<String> getLocalIPList() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    //if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                    if (inetAddress != null) {
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;
    }
}
