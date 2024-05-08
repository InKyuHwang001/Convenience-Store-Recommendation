package com.example.project.direction.service;

import com.example.project.api.dto.DocumentDto;
import com.example.project.api.service.KaKaoCategorySearchService;
import com.example.project.direction.entity.Direction;
import com.example.project.direction.repository.DirectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class DirectionService {

    private static final int MAX_SEARCH_COUNT = 4; //최대 검색 갯수
    private static final double RADIUS_KM = 10.0; //반경 10 km

    private final DirectionRepository directionRepository;
    private final KaKaoCategorySearchService kaKaoCategorySearchService;

    @Transactional
    public List<Direction> saveAll(List<Direction> directionList) {

        if (CollectionUtils.isEmpty(directionList)) return Collections.emptyList();

        return directionRepository.saveAll(directionList);
    }

    public List<Direction> buildDirectionList(DocumentDto inputDocumentDto) {

        if (Objects.isNull(inputDocumentDto)) return Collections.emptyList();

        return kaKaoCategorySearchService.requestStoreCategorySearch(inputDocumentDto.getLatitude(), inputDocumentDto.getLongitude(), RADIUS_KM)
                .getDocumentList()
                .stream().map(storeDto ->
                        Direction.builder()
                                .inputAddress(inputDocumentDto.getAddressName())
                                .inputLatitude(inputDocumentDto.getLatitude())
                                .inputLongitude(inputDocumentDto.getLongitude())
                                .targetStoreName(storeDto.getPlaceName())
                                .targetAddress(storeDto.getAddressName())
                                .targetLatitude(storeDto.getLatitude())
                                .targetLongitude(storeDto.getLongitude())
                                .distance(storeDto.getDistance() * 0.001) //m단위  -> km 단위 변환
                                .build())
                .filter(direction -> direction.getDistance() <= RADIUS_KM)
                .limit(MAX_SEARCH_COUNT)
                .collect(Collectors.toList());

    }
}
