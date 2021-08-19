package com.gh.carrot.carrotmart.controller;

import com.gh.carrot.carrotmart.domain.entity.member.MemberEntity;
import com.gh.carrot.carrotmart.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.gh.carrot.carrotmart.commons.HttpStatusResponseEntity.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 사용자 회원가입 기능
     * @param memberEntity
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registeration(@RequestBody @Valid MemberEntity memberEntity){
        memberService.registrationMember(memberEntity);
        return RESPONSE_OK;
    }
}
