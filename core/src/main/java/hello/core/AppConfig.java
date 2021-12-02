package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 앱 전체를 설정하고 구성한다.
// 역할에 따른 상황별 적절한 구현체 연결을 여기서 담당하게 될 것
public class AppConfig {

    // 멤버리포지토리에 적절한 객체(메모리멤버리포지토리)를 던져주고, 생성자를 통해 해당 클래스의 멤버리포지토리와 연결되어 NPE에 걸리지 않고 원활하게 돌아가게 함
    // 생성자를 통해서 객체(새로운 인스턴스)가 들어간다고 해서 생성자 주입이라고 한다.
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
