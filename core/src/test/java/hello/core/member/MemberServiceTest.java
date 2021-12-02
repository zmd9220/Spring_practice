package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl();
    // 테스트는 appConfig 에서 바로 꺼내기가 애매함 -> BeforeEach를 사용할 것
    // 하나의 테스트 클래스 안에 테스트가 여러개 일 경우 각 테스트마다 동일한 실행 초기 환경을 세팅을 해야 할 경우 @BeforeEach를 사용
    MemberService memberService;

    // 각 테스트 실행전에 무조건 실행되는 부분
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    // 테스트 임포트
    @Test
    void join() {
        // given - 이러한 환경, 데이터가 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when - 이렇게 실행하면
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then - 이렇게 된다.
        // assertions 사용시 org.assertj.core.api 에 있는 것 사용 추천 (편리한 기능이 많음)
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
