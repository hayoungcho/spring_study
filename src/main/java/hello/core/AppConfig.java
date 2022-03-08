package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//앱 실제 동작에 필요한 구현 객체 생성
//생성한 객체 인스턴스의 참조(래퍼런스)를 생성자를 통해서 주입한다.
//관심사 분리 - 공연 기획자역할
//구체 클래스를 선택해서 여기서 설정해줌
//배우들은 각 기능을 실행하는 책임만 하면 됨

//@Configuration
//해당 어노테이션이 붙은 Class를 설정(구성) 정보로 사용
//@Bean 메서드를 모두 호출해 반환된 객체를 스프링 컨테이너에 등록함
//스프링 빈은 메서드 이르므로 등록됨
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService >>> " );
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService >>> ");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //중복해서 사용하기 때문에 중복제거
    //직관적임. 구현체부분을 이 메소드에서 한 번만 변경하면 되기 때문
//    @Bean(name = "mmm")
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository >>> ");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
