package com.gh.carrot.carrotmart.service.member;

import com.gh.carrot.carrotmart.domain.entity.Member;

public interface MemberService {

    // 회원 가입
    public void registrationMember(Member member);

    // 이메일 중복 체크
    public boolean isDuplicatedEmail(String email);

    // 이메일로 회원 찾기
    public Member findMemberByEmail(String email);
}
