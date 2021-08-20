package com.gh.carrot.carrotmart.controller;

import com.gh.carrot.carrotmart.domain.entity.member.Member;
import com.gh.carrot.carrotmart.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.gh.carrot.carrotmart.commons.HttpStatusResponseEntity.RESPONSE_CONFLICT;
import static com.gh.carrot.carrotmart.commons.HttpStatusResponseEntity.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 사용자 회원가입 기능
     * @param member
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registeration(@RequestBody @Valid Member member){ // @valid를 통해 객체를 검증할 수 잇다.검증 방식은 Member에 구현되어 있음
        memberService.registrationMember(member);
        return RESPONSE_OK;
    }

    /**
     * 사용자 이메일 중복체크 기능
     * @param email
     * @return
     */
    @GetMapping("/duplicated/{email}")
    public ResponseEntity<HttpStatus> isDuplicatedEmail(@PathVariable String email){
        if (memberService.isDuplicatedEmail(email)){
           return RESPONSE_CONFLICT;
        }
        return RESPONSE_OK;
    }
}
