package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        // 인터페이스로 조회 -> 인터페이스의 구현체가 대상
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 타입 검색 작성이 간편하지만 같은 타입이 여러개 일 경우 곤란해짐
    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        // 스프링 컨테이너 내에 관련 객체가 들어가 있다면 자동으로 조회 가능하다
        // 컨테이너에는 MemberService 밖에 없음에도 return 객체인 인스턴스 MemberServiceImpl를 보고 결정하기 때문에 return 타입으로도 조회가 가능하다.
        // 다만 구체적으로 적는것을 추천하진 않음 (역할과 구현은 구분할 것, 역할에 의존할 것)
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 실패 테스트
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        // ac.getBean("xxxxx", MemberService.class);
        // NoSuchBean 에러가 나와야함
//        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        // 우측에 있는 로직을 실행했을 때, 왼쪽에 적은 exception이 발생하면 테스트가 성공
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}
