package com.gh.carrot.carrotmart.service.member;

import com.gh.carrot.carrotmart.domain.entity.member.MemberEntity;
import com.gh.carrot.carrotmart.domain.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void registrationMember(MemberEntity memberEntity) {
        memberRepository.save(memberEntity);
    }

    @Override
    public boolean isDuplicatedEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

}
