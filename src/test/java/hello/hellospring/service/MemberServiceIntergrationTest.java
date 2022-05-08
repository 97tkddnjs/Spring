package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  //test에 넣으면 테스트 끝나고 롤백 다 지워짐
class MemberServiceIntergrationTest {

    //먼저 서비스를 만들고
    @Autowired MemberService memberService ;

    //member service의 저장소와 실 저장소가 다를 수 잇음 test case에서
    //그래서 이런 식으로
    @Autowired
    MemberRepository memberRepository ;


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
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

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


}