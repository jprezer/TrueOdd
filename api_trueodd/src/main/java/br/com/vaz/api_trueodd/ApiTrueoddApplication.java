package br.com.vaz.api_trueodd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiTrueoddApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTrueoddApplication.class, args);
    }

}