package net.le.baseframe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("net.le.baseframe.core.**.dao")
public class BaseframeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseframeApplication.class, args);
    }
}
