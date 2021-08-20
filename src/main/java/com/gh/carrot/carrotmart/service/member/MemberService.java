package com.gh.carrot.carrotmart.service.member;

import com.gh.carrot.carrotmart.domain.entity.member.MemberEntity;

public interface MemberService {

    // 회원 가입
    public void registrationMember(MemberEntity memberEntity);

    // 이메일 중복 체크
    public boolean isDuplicatedEmail(String email);

}
