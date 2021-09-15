package com.gh.carrot.carrotmart.domain.dto;

import com.gh.carrot.carrotmart.domain.entity.Address;
import com.gh.carrot.carrotmart.domain.entity.Location;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // 조금더 자세히 알아보기
public class LocationAddressRequest {

    private final String state;
    private final String city;
    private final String town;

    private final Double longitude;
    private final Double latitude;

    public Address toAddress() {
        return Address.builder()
                .state(state)
                .city(city)
                .town(town)
                .build();
    }

    public Location toLocation() {
        return Location.builder()
                .longitude(longitude)
                .latitude(latitude)
                .build();
    }
}