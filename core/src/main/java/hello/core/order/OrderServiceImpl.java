package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // 메모리 멤버 리토지토리
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 정액 할인 정책
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 정률 할인 정책
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 인터페이스만 의존하도록 변경
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원 정보 조회
        Member member = memberRepository.findById(memberId);
        // 설계가 잘 된 예시 - 단일책임 원칙을 잘 지킨 것
        // 할인에 대한 것은 discountPolicy에 맡기고 나는 결과만 받겠다. (즉 이 로직에 대한 변경사항은 discountPolicy에서 처리)
        // 여기서 필요한 정보는 사실 Grade와 가격만 필요하긴한데, 프로젝트 정책이나 상황에 따라 member(미래확장성을 위해?)를 넘겨도 된다.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 주문에 대한 정보를 객체로 만들어서 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
