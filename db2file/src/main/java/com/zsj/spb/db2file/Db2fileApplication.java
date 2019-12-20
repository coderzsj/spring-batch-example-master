package com.zsj.spb.db2file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zsj.spb.db2file.mapper")
@SpringBootApplication
public class Db2fileApplication {

    public static void main(String[] args) {
        SpringApplication.run(Db2fileApplication.class, args);
    }

}
