package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 앱 전체를 설정하고 구성한다.
// 역할에 따른 상황별 적절한 구현체 연결을 여기서 담당하게 될 것
// 스프링 프레임워크로 바꾸기 Configuration, Bean
@Configuration // 해당 클래스가 구성 정보를 담당한다는 의미의 스프링 설정 정보
public class AppConfig {

    // 멤버리포지토리에 적절한 객체(메모리멤버리포지토리)를 던져주고, 생성자를 통해 해당 클래스의 멤버리포지토리와 연결되어 NPE에 걸리지 않고 원활하게 돌아가게 함
    // 생성자를 통해서 객체(새로운 인스턴스)가 들어간다고 해서 생성자 주입이라고 한다.
    @Bean // Bean 으로 명시한 메서드가 스프링 컨테이너에 등록됨
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 멤버서비스와 오더서비스에 멤버리포지토리 관련 객체를 보내주는 역할 - 여기서 사용하기로 한 구현체를 사용하겠다 - 추후 이 부분만 변경하면 갈아끼우기 성공
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 오더서비스에 할인정책 관련 객체를 보내주는 역할 - 여기서 사용하기로 한 구현체를 사용하겠다 - 추후 이 부분만 변경하면 갈아끼우기 성공
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        // 할인 정책 변경
        return new RateDiscountPolicy();
    }
}
