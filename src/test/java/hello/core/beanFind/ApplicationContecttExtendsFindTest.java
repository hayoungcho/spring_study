package hello.core.beanFind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.discount.RateDiscountPolicyTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ApplicationContecttExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이 상 있으면 중복 오류 발생")
    void findBeNbyParentTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }
    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이 상 있으면 빈 이름 지정하면 됨")
    void findBeNbyParentName(){
        DiscountPolicy radeDiscountPolicy = ac.getBean("rateDiscountPolicy", RateDiscountPolicy.class);
        assertThat(radeDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    //추천하지 않는 방식
    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeNbySubType(){
        RateDiscountPolicy radeDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        assertThat(radeDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeanByPsrentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key);
            System.out.println("value = " +beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeYbYoBEJCTtYPE(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key);
            System.out.println("value = " +beansOfType.get(key));
        }

    }

    @Configuration
    static  class TestConfig{
        //설계시
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public  DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }


}
