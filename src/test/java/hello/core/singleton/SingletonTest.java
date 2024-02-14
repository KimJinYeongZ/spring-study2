package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer()
    {
        AppConfig appConfig  = new AppConfig();
        //1.조회: 호촐할 때 마다 객체를 생성
        MemberService memberService = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        //1 =/=2
        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest()
    {
        SingletonService singletonService = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService = " + singletonService);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService).isSameAs(singletonService2);
        //same 과 equal의 차이?
        //same  ==
        //equal 자바의 equals
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1.조회: 호촐할 때 마다 객체를 생성
        MemberService memberService = ac.getBean("memberService",MemberService.class);

        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService).isSameAs(memberService2);

    }
}
