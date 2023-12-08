package com.carfix;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class 람다테스트 {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    void 람다테스트1(){
        List<String> list = List.of("apple", "banana", "grape");

        // stream
        list
        .stream() // 계속 줄줄이 이어지다
        .filter(e -> e.startsWith("b"))
        .forEach(e -> logger.info("### {}", e));
    }

    @Test
    void 람다테스트2(){
        List<String> list = List.of("apple", "banana", "grape");
        list = list
        .stream()
        .map(e -> e.toUpperCase()) // stream
        .collect(Collectors.toList()); // stream to list
        logger.info("### {}", list);
    }

    @Test
    void 메소드레퍼런스(){
        List<String> list = List.of("apple", "banana", "grape");
        list = list
        .stream()
        .map(String::toUpperCase) // e -> e.toUpperCase()
        .collect(Collectors.toList());

        logger.info("### {}", list);
    }
}
