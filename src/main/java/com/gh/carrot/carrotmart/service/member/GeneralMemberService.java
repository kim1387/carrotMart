package com.gh.carrot.carrotmart.service.member;

import com.gh.carrot.carrotmart.domain.dto.MemberDto;
import com.gh.carrot.carrotmart.domain.dto.ProfileRequest;
import com.gh.carrot.carrotmart.domain.entity.Member;
import com.gh.carrot.carrotmart.domain.repository.MemberRepository;
import com.gh.carrot.carrotmart.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GeneralMemberService implements MemberService{

    private final MemberRepository memberRepository;
    private final LoginService loginService;

    @Override
    @Transactional
    public void registrationMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public boolean isDuplicatedEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public Member findMemberByEmail(String email) {
       return memberRepository.findMemberByEmail(email).orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member findMemberById(long id) {
        return memberRepository.findMemberById(id).orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public boolean isValidMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
        Member member = findMemberByEmail(memberDto.getEmail());
        return passwordEncoder.matches(memberDto.getPassword(), member.getPassword());
    }

    @Override
    @Transactional
    public void updateMemberProfile(Member member, ProfileRequest profileRequest) {
        member.updateProfile(profileRequest.getNickname());
    }


}
