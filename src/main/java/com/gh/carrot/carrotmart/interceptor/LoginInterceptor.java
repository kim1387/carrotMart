package com.gh.carrot.carrotmart.interceptor;

import com.gh.carrot.carrotmart.commons.annotation.LoginRequired;
import com.gh.carrot.carrotmart.exception.UnAuthorizedAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String MEMBER_ID = "MEMBER_ID";

    // preHandle 은 컨트롤러에 요청이 넘겨지기 이전에
    // postHandle 은 컨트롤러가 처리를 마친 후
    // afterCompletion 은 view 까지 모든 요청처리가 완료되었을 때 호출된다.
    // 관련 이미지 https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FSCls1%2FbtqKtenwkkP%2F9XU9YrKM32Ld2YvPiim0Uk%2Fimg.jpg
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        String memberId = (String) request.getSession().getAttribute(MEMBER_ID);
        // 궁금증? Member로만 바꾸기만 햇는데 반환되는 값이 달라짐/??
        // Member member = (Member) request.getSession().getAttribute(MEMBER_ID);
        Long memberId = (Long) request.getSession().getAttribute(MEMBER_ID);
        if (memberId !=null){
            return true;
        }
        if (handlerMethod.hasMethodAnnotation(LoginRequired.class)) {
            throw new UnAuthorizedAccessException();
        }
        return true;
    }
}
//Spring Interceptor
//역할 : dispatcher servlet과 controller 사이에서 HttpRequest, HttpResponse를 가로채는 역할을 한다.
// 컨트롤러에 요청이 도착하기 전에 해당 유저가 컨트롤러에 접근할 권한이 있는지 세션을 확인한다거나,
// 처리 결과에 따라서 응답을 돌리고자 하는 경우에 자주 사용된다.
// filter의 경우 dispatcherServlet이 실행되기 전, interceptor의 경우 실행 이후에 호출된다는 차이가 있다.
// [참고 자료]https://ecsimsw.tistory.com/entry/Spring-Interceptor-Spring-boot

// request -> filter -> dispatcher servlet -> handlerInterceptor(preHandler)  -> Controller -> handlerInterceptor(postHandler)