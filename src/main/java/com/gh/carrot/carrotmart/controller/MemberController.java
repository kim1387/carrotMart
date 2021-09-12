package com.gh.carrot.carrotmart.controller;

import com.gh.carrot.carrotmart.domain.dto.MemberDto;
import com.gh.carrot.carrotmart.domain.entity.Member;
import com.gh.carrot.carrotmart.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.gh.carrot.carrotmart.commons.HttpStatusResponseEntity.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberController {

    private static final String MEMBER_ID = "MEMBER_ID";
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 사용자 회원가입 기능
     *
     * @param memberDto
     * @return ResponseEntity<HttpStatus>
     */
    @PostMapping
    public ResponseEntity<HttpStatus> registeration(@RequestBody @Valid MemberDto memberDto){ // @valid를 통해 객체를 검증할 수 잇다.검증 방식은 Member에 구현되어 있음
        Member member = MemberDto.toEntity(memberDto, passwordEncoder);

        memberService.registrationMember(member);
        return RESPONSE_OK;
    }

    /**
     * 사용자 이메일 중복체크 기능
     *
     * @param email
     * @return ResponseEntity<HttpStatus>
     */
    @GetMapping("/duplicated/{email}")
    public ResponseEntity<HttpStatus> isDuplicatedEmail(@PathVariable String email){
        if (memberService.isDuplicatedEmail(email)){
           return RESPONSE_CONFLICT;
        }
        return RESPONSE_OK;
    }

    /**
     * 사용자 로그인
     *
     * @param memberDto
     * @param httpSession
     * @return ResponseEntity<HttpStatus>
     */
    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody @Valid MemberDto memberDto, HttpSession httpSession){
        Member member = memberService.findMemberByEmail(memberDto.getEmail());

        //boolean matches(String raw, String encoded) : 평문 패스워드와 암호화 패스워드가 같은 패스워드인지 비교
        if (passwordEncoder.matches(memberDto.getPassword(),member.getPassword())){
            httpSession.setAttribute(MEMBER_ID,member.getId());
            return RESPONSE_OK;
        }
        return RESPONSE_BAD_REQUEST;
    }


    /**
     * 사용자 로그아웃 기능
     *
     * @param httpSession
     * @return ResponseEntity<HttpStatus>
     */
    @GetMapping("/logout")
    public ResponseEntity<HttpStatus> logout(HttpSession httpSession) {
        httpSession.removeAttribute(MEMBER_ID);
        return RESPONSE_OK;
    }
}
