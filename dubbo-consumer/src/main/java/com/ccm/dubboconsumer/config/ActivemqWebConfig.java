package com.ccm.dubboconsumer.config;

import org.apache.activemq.web.AjaxServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chencm on 2018/12/14
 * 配置activemq可被前端正常消费
 */
@Configuration
public class ActivemqWebConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public ServletRegistrationBean amqServletRegistrationBean() {
        //资源目录必须设置为根目录，不然访问amq会跟相对路径，路由会报错
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new AjaxServlet());
        registrationBean.setLoadOnStartup(1);
        List<String> urlMappings=new ArrayList<String>();
        urlMappings.add("/amq/*");
        registrationBean.setUrlMappings(urlMappings);
        return registrationBean;
    }

    @Bean
    public ServletContextInitializer initializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setInitParameter("org.apache.activemq.brokerURL", brokerUrl);
            }
        };
    }
}
