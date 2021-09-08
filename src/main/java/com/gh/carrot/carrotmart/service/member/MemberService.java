package com.gh.carrot.carrotmart.service.member;

import com.gh.carrot.carrotmart.domain.dto.MemberDto;

public interface MemberService {

    // 회원 가입
    public void registrationMember(MemberDto member);

    // 이메일 중복 체크
    public boolean isDuplicatedEmail(String email);

}
