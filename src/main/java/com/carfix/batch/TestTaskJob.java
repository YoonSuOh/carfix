package com.carfix.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestTaskJob {

    @Scheduled(cron = "1 * * * * *") // 매일 1분마다 실행
    public void test(){
        // Job 내용
        log.info("#### test job 수행");
    }
}
