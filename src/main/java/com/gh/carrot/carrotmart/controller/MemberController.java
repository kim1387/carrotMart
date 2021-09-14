package com.gh.carrot.carrotmart.controller;

import com.gh.carrot.carrotmart.commons.annotation.LoginRequired;
import com.gh.carrot.carrotmart.domain.dto.MemberDto;
import com.gh.carrot.carrotmart.domain.entity.Member;
import com.gh.carrot.carrotmart.service.member.LoginService;
import com.gh.carrot.carrotmart.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.gh.carrot.carrotmart.commons.HttpStatusResponseEntity.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberController {

    // 문득 든 생각... 왜 인터페이스를 사용 하는가... 결론 OCP(개방폐쇄 원리)
    // interface와 class가 1:1 관계 일 때도 인터페이스가 사용이 되어야하는가...? 라는 질문.. 작년부터 궁금했다.
    // 작년엔 cs 지식이 모자라서 이해를 못했지만.. OCP 원리 때문이라고 한다. 이때문에 spring offical docs에서도 interface 사용을 권장한다.
    // 결론 찬반(?) 의견이 갈림
    // 반대: 관례적으로 무조건 사용하는 것은 지양해야한다. 그렇다고 interface를 만들지 말라는 의미는 x
    // 찬성: 비록 현재는 바뀌지 않고 불필요해 보이지 않지만 서비스는 계속 변화 한다. 개발자는 항상 미래의 변화를 대비 해야하므로 만드는 것이 좋다. -TOBY-
    // [참고자료]
    // https://see-one.tistory.com/1?category=948566 - Spring Security service 구현체 관련
    // https://codevang.tistory.com/312  -- 스프링 의존 주입(DI)과 인터페이스 사용에 관하여
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final LoginService loginService;

    /**
     * 사용자 회원가입 기능
     *
     * @param memberDto
     * @return ResponseEntity<HttpStatus>
     */
    @PostMapping
    public ResponseEntity<HttpStatus> registeration(@RequestBody @Valid MemberDto memberDto){ // @valid를 통해 객체를 검증할 수 잇다.검증 방식은 Member에 구현되어 있음

        // 클라이언트에서 사용자 이메일 중복체크를 수행하지만 API 요청에 의한 예외상황에 대비하여 더블체크
        boolean isDuplicated = memberService.isDuplicatedEmail(memberDto.getEmail());
        if (isDuplicated){
            return RESPONSE_CONFLICT;
        }

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
     * @return ResponseEntity<HttpStatus>
     */
    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody @Valid MemberDto memberDto){

        // Member member = memberService.findMemberByEmail(memberDto.getEmail());
        boolean isValidMember = memberService.isValidMember(memberDto,passwordEncoder);
        if (isValidMember){
            loginService.login(memberDto.getEmail());
            return RESPONSE_OK;
        }
        //boolean matches(String raw, String encoded) : 평문 패스워드와 암호화 패스워드가 같은 패스워드인지 비교
//        if (passwordEncoder.matches(memberDto.getPassword(),member.getPassword())){
//            httpSession.setAttribute(MEMBER_ID,member.getId());
//            return RESPONSE_OK;
//        }
        return RESPONSE_BAD_REQUEST;
    }


    /**
     * 사용자 로그아웃 기능
     *
     * @return ResponseEntity<HttpStatus>
     */
    @LoginRequired //로그아웃 기능에 대해서 로그인 여부를 체크하는 어노테이션 추가
    @GetMapping("/logout")
    public ResponseEntity<HttpStatus> logout() {
        loginService.logout();
        return RESPONSE_OK;
    }
}
