package com.gh.carrot.carrotmart.domain.dto;

import com.gh.carrot.carrotmart.domain.entity.Address;
import com.gh.carrot.carrotmart.domain.entity.Location;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // 조금더 자세히 알아보기
public class LocationAddressRequest {

    private final String depth1;
    private final String depth2;
    private final String depth3;

    private final Double longitude;
    private final Double latitude;

    public Address toAddress() {
        return Address.builder()
                .depth1(depth1)
                .depth2(depth2)
                .depth3(depth3)
                .build();
    }

    public Location toLocation() {
        return Location.builder()
                .longitude(longitude)
                .latitude(latitude)
                .build();
    }
}