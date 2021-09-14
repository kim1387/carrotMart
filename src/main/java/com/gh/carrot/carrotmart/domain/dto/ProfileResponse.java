package com.gh.carrot.carrotmart.domain.dto;

import com.gh.carrot.carrotmart.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class ProfileResponse {

    private final Long id;
    private final String email;
    private final String nickname;

    public static ProfileResponse of(Member member) {
        return ProfileResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}