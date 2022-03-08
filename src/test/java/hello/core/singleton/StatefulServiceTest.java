package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService test1 = ac.getBean(StatefulService.class);
        StatefulService test2 = ac.getBean(StatefulService.class);

        //ThreadA : A 사용자가 10000원 주문
        int userA = test1.order("userA", 1000);

        //ThreadB : B 사용자가 20000원 주문
        test2.order("userB", 2000);
        System.out.println("user A = " + userA);
        //ThreadA : 사용자 A가 주문 금액 조회
//        int price = test1.getPrice();
//        System.out.println("price = " + price);

//        Assertions.assertThat(test1.getPrice()).isEqualTo(2000);

    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}