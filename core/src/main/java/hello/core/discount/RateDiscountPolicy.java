package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            // 제대로 나오나 테스트 필요
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
