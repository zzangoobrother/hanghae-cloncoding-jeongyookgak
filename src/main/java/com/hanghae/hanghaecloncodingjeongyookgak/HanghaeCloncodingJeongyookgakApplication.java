package com.hanghae.hanghaecloncodingjeongyookgak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaeCloncodingJeongyookgakApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghaeCloncodingJeongyookgakApplication.class, args);
    }

}
