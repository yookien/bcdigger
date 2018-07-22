package com.bcdigger;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.github.tobato.fastdfs.FdfsClientConfig;

@SpringBootApplication
@ComponentScan
@MapperScan("com.bcdigger.*.dao")
@Import(FdfsClientConfig.class)
@ServletComponentScan//扫描编写的servlet和filter（bruid）
public class BCDiggerApp {

	public static void main(String[] args) {
		SpringApplication.run(BCDiggerApp.class, args);
		//new SpringApplicationBuilder(BCDiggerApp.class).web(true).run(args);
	}

}
