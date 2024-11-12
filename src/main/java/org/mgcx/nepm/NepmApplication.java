package org.mgcx.nepm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.mgcx.nepm.mapper")
public class NepmApplication {

    public static void main(String[] args) {
        SpringApplication.run(NepmApplication.class, args);
    }

}
