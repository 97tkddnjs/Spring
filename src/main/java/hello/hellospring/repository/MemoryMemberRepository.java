package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    //메모리니까 저장해야 할 공간이 필요한 데 Map으로 받아들이겠다.
    private static Map<Long , Member> store = new HashMap<>(); //동시성 문제가 있을 수 잇어서 concurrentHash가 필요하나 예제이므로 HashMap사용
    //
    private static long sequence = 0L;//(0,1,2) 값을 생성해주는 친구

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //return store.get(id); //Null이 반환될 가능성 존재함
        //따라서 요즘은 이렇게
        return Optional.ofNullable(store.get(id)); // 감싸서 반환해주면 클라이언트에서 뭔가 하기 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member-> member.getName().equals(name))
                .findAny(); //loop를 돌면서 찾으면 그 값 반환 없으면 Null값 반환
        //return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //member들이 쭉 반환됨
    }

    public void clearStore(){
        store.clear();
    }
}
//전체가 잘 돌아가는 지 확인하는 방법은 테스트 케이스를 만들어 보는 것이다.
//test에 하면 됨