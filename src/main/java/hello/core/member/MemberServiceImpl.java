package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    //생성자를 통해 어떤 구현객체가 들어올지 모름
    // 생성자를 통해 어떤 구현 객체를 주입할지는 오직 외부에서 결정됨
    //의존관계에 대한 고민은 외부테 맡기고 실행에만 집중한다. join, findMember
    // DI ( Dependency Injection : 의존성 주입)
    @Autowired //(ac.getBean(MemberRepository.class)) 와 같음
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
