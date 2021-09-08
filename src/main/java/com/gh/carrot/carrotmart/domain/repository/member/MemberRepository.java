package com.gh.carrot.carrotmart.domain.repository.member;

import com.gh.carrot.carrotmart.domain.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberDto, Long> {
    public boolean existsByEmail(String email);
}
