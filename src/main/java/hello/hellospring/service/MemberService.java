package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {


   private final MemberRepository memberRepository;

   //외부에서 메모리를 가져오는 것 DI(Dependency Injection)

   public MemberService(MemberRepository memberRepository){
       this.memberRepository = memberRepository;
   }

    /**
     *  회원가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 X
        //findByName이 Optional 이므로 밑에 코드 보단.
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m ->{ // Null 가능성 있으면 요즘은 Optional 사용한다.

            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/


        //findByName이 Optional 이므로 이렇게.
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member); // 함수 추출출
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
            // Null 가능성 있으면 요즘은 Optional 사용한다.

            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findmembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
