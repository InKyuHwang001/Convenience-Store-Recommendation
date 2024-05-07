package com.example.project.store.service;

import com.example.project.api.dto.ApiResponseDto;
import com.example.project.api.dto.DocumentDto;
import com.example.project.api.service.KakaoAddressSearchService;
import com.example.project.direction.entity.Direction;
import com.example.project.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendationStoreList(String address){
        ApiResponseDto apiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(apiResponseDto) || CollectionUtils.isEmpty(apiResponseDto.getDocumentList())) {
            log.error("[StoreRecommendationService.recommendationStoreList fail] Input address: {}", address);
            return ;
        }

        DocumentDto documentDto = apiResponseDto.getDocumentList().get(0);

        List<Direction> directions = directionService.buildDirectionList(documentDto);

        directionService.saveAll(directions);
    }
}
