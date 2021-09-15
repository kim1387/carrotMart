package com.gh.carrot.carrotmart.service.member;

import com.gh.carrot.carrotmart.domain.dto.MemberDto;
import com.gh.carrot.carrotmart.domain.dto.PasswordRequest;
import com.gh.carrot.carrotmart.domain.dto.ProfileRequest;
import com.gh.carrot.carrotmart.domain.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface MemberService {

    // 회원 가입
    void registrationMember(Member member);

    // 이메일 중복 체크
    boolean isDuplicatedEmail(String email);

    // 이메일로 회원 찾기
    Member findMemberByEmail(String email);
    // ID로 회원 찾기
    Member findMemberById(long id);

    // 유효한 맴버인지 확인
    boolean isValidMember(MemberDto memberDto, PasswordEncoder passwordEncoder);

    // 유효한 비밀번호인지 확인
    boolean isValidPassword(Member member, PasswordRequest passwordRequest, PasswordEncoder passwordEncoder);

    // 사용자 정보 업데이트 기능 구현
    void updateMemberProfile(Member member, ProfileRequest profileRequest);

    // 비밀번호 업데이트
    void updateMemberPassword(Member member, PasswordRequest passwordRequest, PasswordEncoder passwordEncoder);

}
