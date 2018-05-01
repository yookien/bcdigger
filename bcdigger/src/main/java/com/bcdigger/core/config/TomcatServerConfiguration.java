package com.bcdigger.core.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * 对Springboot自带的tomcat进行调优
* <p>Title: TomcatServerConfiguration</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年5月1日
 */
public class TomcatServerConfiguration {
	@Bean  
    public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory(){  
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        //设置tomcat启动端口
        tomcatFactory.setPort(8080);
        tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());  
        return tomcatFactory;  
    }  
}

class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer{  
    public void customize(Connector connector)  
    {  
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();  
        //设置最大连接数  
        protocol.setMaxConnections(2000);  
        //设置最大线程数  
        protocol.setMaxThreads(2000); 
        //设置连接超时时间
        protocol.setConnectionTimeout(30000);  
    }  
}  