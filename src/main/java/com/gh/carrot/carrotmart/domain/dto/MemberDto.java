package com.gh.carrot.carrotmart.domain.dto;

import com.gh.carrot.carrotmart.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Builder
public class MemberDto {
    // @Email을 통한 유효한 Email인지 검증한다. - 이 어노테이션이 정확한지도 확인이 필요
    // @Email 은 이메일 형식이 아닌경우 예외를 던지도록 설정할 수 있습니다. 하지만 우리가 원하는 정규식과 다름으로 직접 정규식을 작성
    @NotEmpty
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private final String email;

    @NotEmpty
    @Pattern(message = "최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력해야 합니다.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!~$%^&-+=()])(?=\\S+$).{8,16}$")
    private final String password;

    @NotEmpty
    private final String nickname;

    public static Member toEntity(MemberDto memberDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .nickname(memberDto.getNickname())
                .build();
    }
    // @NotNull & @NotEmpty & @NotBlank 차이
    // Bean Validation이 제공하는 표준 validation이다.

    // @NotNull 은 이름 그대로 Null만 허용하지 않습니다.
    //따라서, "" 이나 " " 은 허용하게 됩니다

    // @NotEmpty 는 null 과 "" 둘 다 허용하지 않게 합니다.
    //@NotNull 에서 "" validation 이 추가된 것입니다.

    //@NotEmpty 는 null 과 "" 과 " " 모두 허용하지 않습니다.
    //@NotEmpty 에서 " " validation 이 추가된 것입니다.

    //https://sanghye.tistory.com/36


    // 번외: 헷갈리는 개념이었는데 블로그에 나와서 정리
    // DTO 를 사용하는 이유는,
    // User 라는 Domain 에 name 이 모든 request 및 response 에 필요하지 않음에도 불필요하게 사용될 수 있으며,
    // 만약 각 API 의 request 와 response 에 맞추기 위해 domain 이 수정되서는 안되기 떄문입니다.
    // 따라서 4각 DTO 에 필요한 데이터만 정의 되어야하며,
    // 필수 값에 대한 조건 체크하는 것이나 DTO 에서 Domain 으로 변환하거나,
    // Domain 에서 DTO 로 변환하는 로직은 Domain 이 아닌 DTO 에 담겨야 합니다.
    // 따라서, 위의 @NotNull 과 같은 Data 의 Validation 도 DTO 의 역할!
    // 도메인이 아닌! DTO 에 넣어주게 되면 역할과 책임이 좀 더 명백해지게 됩니다.

    // peeppeep의 모델도 이걸로 쓰면... 원하는 값만 가져오지 않을까...

}
