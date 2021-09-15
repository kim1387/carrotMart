package com.gh.carrot.carrotmart.service.member;

import com.gh.carrot.carrotmart.domain.entity.Member;

public interface LoginService {

    void login(Member member);

    void logout();

    Member getLoginMember(Long id);
}