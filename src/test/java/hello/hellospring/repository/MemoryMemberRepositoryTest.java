package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메소드 함수의 실행이 끝날 떼 마다 동작하는 콜백 함수 같은 개념
    public void afterEach()
    {
        //순서가 끝날 때마다 없애줌
        repository.clearStore();
    }
    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //optional에서는 get을 통해서 꺼냄
        repository.findById(member.getId()).get();

        Member result = repository.findById(member.getId()).get();
        // 이렇게 하면 true가 문자로 나온다
        //System.out.println("result = "+ (result ==member));

        //같으면 그대로 다르면 빨간 결과 ,result를 변경해볼 것
        //Assertions.assertEquals(member,result);
        //Assertions.assertEquals(member,actual : null);

        //좀 더 편리한 방법 Assertions 앞부분은 위에게 없으면 될 것임
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        repository.findByName("spring1");

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }
    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2); //사이즈는 두개가 나옴
        // 정수 2 부분 바꾸면서 테스트~
    }
}