package com.carfix;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CarfixApplicationTests {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void 검사_테스트(){
		String str = "";
		if(ObjectUtils.isEmpty(str)){ // null or ""
			logger.info("##### str은 null 또는 비어있다.");
		}

		// List<Integer> list = null;
		List<Integer> list = new ArrayList<>();
		if(ObjectUtils.isEmpty(list)){
			logger.info("$$$$$ list는 null이거나 비어있다.");
		}
	}
	@Test
	void contextLoads() {
	}
}
