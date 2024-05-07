package com.example.project.api.service;

import com.example.project.api.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class KaKaoCategorySearchService {

    private final KaKaoUriBuilderService kaKaoUriBuilderService;
    private final RestTemplate restTemplate;
    private static final String STORE_CATEGORY = "CS2";

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    public ApiResponseDto requestStoreCategorySearch(double latitude, double longitude, double radius) {

        var uri = kaKaoUriBuilderService.buildUriByCategorySearch(latitude, longitude, radius, STORE_CATEGORY);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK "+ kakaoRestApiKey);
        var httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, GET, httpEntity, ApiResponseDto.class).getBody();
    }

}
