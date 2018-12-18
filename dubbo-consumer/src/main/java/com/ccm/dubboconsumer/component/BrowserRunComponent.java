package com.ccm.dubboconsumer.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by chencm on 2018/12/17
 */
@Component
@Slf4j
public class BrowserRunComponent implements CommandLineRunner {
    @Value("${spring.web.defaultUrl}")
    private String defaultUrl;

    @Value("${spring.web.browserPath}")
    private String browserPath;

    @Value("${spring.web.autoOpen}")
    private boolean autoOpen;
    @Override
    public void run(String... args) throws Exception {
        if(autoOpen){
            String cmd = browserPath +" "+ defaultUrl;
            Runtime run = Runtime.getRuntime();
            try{
                run.exec(cmd);
                log.debug("启动浏览器打开项目成功");
            }catch (Exception e){
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
    }
}
