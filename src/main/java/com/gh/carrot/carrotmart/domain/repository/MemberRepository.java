package com.gh.carrot.carrotmart.domain.repository;

import com.gh.carrot.carrotmart.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

    // 오류는 아니지만 반환 값을 매소드에 명시
    Optional<Member> findMemberByEmail(String email);
}
