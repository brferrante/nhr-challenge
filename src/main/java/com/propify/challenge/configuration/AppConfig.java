package com.propify.challenge.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.propify.challenge.mapper")
public class AppConfig {
    //Disclaimer: I have absolutely no idea how mybatis works, this is the first time I'm seeing it.
}
