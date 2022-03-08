package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


// ComponentScan : @Componenet 어네토이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록
// Default scan 대상은 @Component scan이 붙은 클래스의 패키지가 시작위치가 됨
// 권장방법 : 설정정보 클래스 위치를 프로젝트 최상단에 두기 -> 최근 스프링 부트도 이 방법으로 제공
@Configuration
@ComponentScan(
//        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
