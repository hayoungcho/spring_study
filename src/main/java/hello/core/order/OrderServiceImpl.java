package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{


    //어떠한 구현객체에도 의존하지 않음.
    //단지 Interface 에만 의존 -> DIP
    //구현객체는 AppConfig(외부) 에서 결정한다.
    //실행에만 집중하면 됨
    //구체적인 new X (=구현체가 없다는)
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;

    //생성자가 하나일 때는 자동으로 Autowired 적용됨
    //아래 코드는 DIP위반 -> 구현객체에 의존했기 때문 : 인터페이스에 의존해야함
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Test
    void fieldInjectionTest(){
//        OrderServiceImpl orderservice = new OrderServiceImpl();
    }
}
