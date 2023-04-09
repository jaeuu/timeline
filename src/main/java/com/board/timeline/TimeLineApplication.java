package com.board.timeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing// ** jpa를 사용하면 넣어줘야함. 데이터 변동(생성시간 또는 수정시간)이 있으면 jpa가 알아서 반영하도록 하기위해)
@SpringBootApplication
public class TimeLineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeLineApplication.class, args);
    }

}
