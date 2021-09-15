package com.gh.carrot.carrotmart.service.member;

import com.gh.carrot.carrotmart.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

    private final HttpSession httpSession;
    private final MemberService memberService;

    private static final String MEMBER_ID = "MEMBER_ID";

    @Override
    public void login(long id) {
        httpSession.setAttribute(MEMBER_ID, id);
    }

    @Override
    public void logout() {
        httpSession.removeAttribute(MEMBER_ID);
    }

    @Override
    public Member getLoginMember(Long id) {
        // return (Member) httpSession.getAttribute(MEMBER_ID);
        Long memberId = (Long) httpSession.getAttribute(MEMBER_ID);

        return memberService.findMemberById(memberId);
    }
}