package com.bcdigger;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan("com.bcdigger.*.dao")
public class BCDiggerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SpringApplicationBuilder(BCDiggerApp.class).web(true).run(args);
	}

}
