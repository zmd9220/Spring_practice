package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    // 순수한 자바코드로 만든 자바 메서드 실행 (스프링 관련 X)
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
        // AppConfig 를 통해 의존관계 주입(관심사 분리)
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        // 스프링 코드로 변경
        // 스프링은 ApplicationContext 로 시작 (스프링 컨테이너)
        // AnnotationConfigApplicationContext = Annotation 기반으로 Config 된 Class 를 가져와 컨테이너로 만든다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // Bean으로 등록된 컨테이너 메서드의 이름은 기본적으로 해당 메서드의 이름으로 등록되어 있음
        // getBean - memberService 라는 이름의 메서드에서 class 형태로 반환을 받아오겠다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // Long 형태이므로 1L 이라고 적어야함
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        // 이러한 어플리케이션 로직으로 테스트하는 것은 한계가 존재함 (매번 원하는 위치에 출력하도록 하던가 하는 번거로움 + 눈으로 확인해야함)
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
