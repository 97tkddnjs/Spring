package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MemoryMemberRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //먼저 서비스를 만들고
    MemberService memberService ;

    //member service의 저장소와 실 저장소가 다를 수 잇음 test case에서
    //그래서 이런 식으로
    MemoryMemberRepository memberRepository ;


    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 메소드 함수의 실행이 끝날 떼 마다 동작하는 콜백 함수 같은 개념
    public void afterEach()
    {
        //순서가 끝날 때마다 없애줌 repository를
        memberRepository.clearStore();
    }

    @Test //테스트는 과감하게 한글로 해도 ㄱㅊ
    void join() { //여기도 그냥 한글 써도 됨
        //given 주어졌는 데
        Member member = new Member();
        member.setName("hello");

        //when 실행햇는 데
        Long saveId = memberService.join(member);
        //then 이런 결과 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()). isEqualTo(findMember.getName());

    }
    @Test
    public void 중복_회ㅣ원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);


        //밑에 주석이랑 같은 의미
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try{
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }





    }

    @Test
    void findmembers() {
    }

    @Test
    void findOne() {
    }
}