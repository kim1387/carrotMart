package com.gh.carrot.carrotmart.commons.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD}) // Java compiler 가 annotation 이 어디에 적용될지 결정하기 위해 사용합니다. // 메서드 선언
@Retention(RetentionPolicy.RUNTIME) //컴파일 전까지만 유효합니다. 즉, 컴파일 이후에는 사라지게 됩니다.
@Documented
public @interface LoginRequired {
}
//[참고 자료]https://sanghye.tistory.com/39