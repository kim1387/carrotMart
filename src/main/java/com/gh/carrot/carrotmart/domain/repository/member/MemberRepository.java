package com.gh.carrot.carrotmart.domain.repository.member;

import com.gh.carrot.carrotmart.domain.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
