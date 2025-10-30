package com.cakmak.dtos;

public record LivestreamDto(
        String id,
        String url,
        Boolean membershipRequired,
        Integer price
) {
}
