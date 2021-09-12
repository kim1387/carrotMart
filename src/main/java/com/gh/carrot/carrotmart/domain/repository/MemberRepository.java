package com.gh.carrot.carrotmart.domain.repository;

import com.gh.carrot.carrotmart.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Member findByEmail(String email);
}
