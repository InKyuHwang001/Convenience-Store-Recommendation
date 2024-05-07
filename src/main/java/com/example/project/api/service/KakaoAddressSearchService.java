package com.example.project.api.service;

import com.example.project.api.dto.ApiResponseDto;
import com.example.project.exception.RuntimeException.ProjectException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoAddressSearchService {

    private final RestTemplate restTemplate;
    private final KaKaoUriBuilderService kaKaoUriBuilderService;

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;


    @Retryable(retryFor = {ProjectException.class},
            maxAttempts = 2,
            backoff = @Backoff(delay = 3_000) //3초
    )
    public ApiResponseDto requestAddressSearch(String address) {

        if (ObjectUtils.isEmpty(address)) return null;

        URI uri = kaKaoUriBuilderService.buildUriByAddressSearch(address);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        HttpEntity httpEntity = new HttpEntity<>(headers);

        //kakao api 호출
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, ApiResponseDto.class).getBody();
    }

    @Recover
    public ApiResponseDto recover(ProjectException e, String address){
        log.error("All the retries are failed. address: {}, error: {}", address, e.getMessage());

        //TODO: 예외 처리하기
        return null;
    }
}
