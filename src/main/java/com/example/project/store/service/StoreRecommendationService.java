package com.example.project.store.service;

import com.example.project.api.dto.ApiResponseDto;
import com.example.project.api.dto.DocumentDto;
import com.example.project.api.service.KakaoAddressSearchService;
import com.example.project.direction.entity.Direction;
import com.example.project.direction.service.Base62Service;
import com.example.project.direction.service.DirectionService;
import com.example.project.store.dto.InputDto;
import com.example.project.store.dto.OutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;
    private final Base62Service base62Service;

    @Value("${store.recommendation.base.url}")
    private String baseUrl;


    public List<OutputDto> recommendationStoreList(InputDto request){
        String address = request.getAddress();
        ApiResponseDto apiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(apiResponseDto) || CollectionUtils.isEmpty(apiResponseDto.getDocumentList())) {
            log.error("[StoreRecommendationService.recommendationStoreList fail] Input address: {}", address);
            return Collections.emptyList();
        }

        DocumentDto documentDto = apiResponseDto.getDocumentList().get(0);

        List<Direction> directions = directionService.buildDirectionList(documentDto);

        return directionService.saveAll(directions).stream()
                .map(entity -> {
                    OutputDto outputDto = OutputDto.fromEntity(entity);
                    outputDto.makeShortenDirectionUrl(baseUrl + base62Service.encodeDirectionId(entity.getId()));
                    return outputDto;
                })
                .collect(Collectors.toList());
    }
}
