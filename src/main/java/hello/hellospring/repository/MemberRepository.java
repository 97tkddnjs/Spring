package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // optional java 8부터의 기능
    // 없으면 NULL 반환 -> But 요즘은 이걸로 NUll처리
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
