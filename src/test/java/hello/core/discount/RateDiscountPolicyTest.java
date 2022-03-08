package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicyTest implements DiscountPolicy {

    private int discountPolicy = 10;


    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return price * discountPolicy / 10;
        else
            return 0;
    }
}