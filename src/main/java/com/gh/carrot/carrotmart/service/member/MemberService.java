package com.gh.carrot.carrotmart.service.member;

import com.gh.carrot.carrotmart.domain.dto.MemberDto;
import com.gh.carrot.carrotmart.domain.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface MemberService {

    // 회원 가입
    void registrationMember(Member member);

    // 이메일 중복 체크
    boolean isDuplicatedEmail(String email);

    // 이메일로 회원 찾기
    Member findMemberByEmail(String email);

    // 유효한 맴버인지 확인
    boolean isValidMember(MemberDto memberDto, PasswordEncoder passwordEncoder);
}
