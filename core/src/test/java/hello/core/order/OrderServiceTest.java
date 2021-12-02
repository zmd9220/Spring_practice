package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();
    // 테스트는 appConfig 에서 바로 꺼내기가 애매함 -> BeforeEach를 사용할 것
    // 하나의 테스트 클래스 안에 테스트가 여러개 일 경우 각 테스트마다 동일한 실행 초기 환경을 세팅을 해야 할 경우 @BeforeEach를 사용
    MemberService memberService;
    OrderService orderService;

    // 각 테스트 실행전에 무조건 실행되는 부분
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // long(primitive) 타입으로 적어도 괜찮지만 null을 사용할 수 없다.
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
