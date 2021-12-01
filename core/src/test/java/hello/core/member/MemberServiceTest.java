package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

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
