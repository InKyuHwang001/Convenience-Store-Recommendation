package com.example.project.direction.service;

import com.example.project.api.dto.DocumentDto;
import com.example.project.direction.entity.Direction;
import com.example.project.store.dto.StoreDto;
import com.example.project.store.service.StoreSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectionService {

    private static final int MAX_SEARCH_COUNT = 4; //최대 검색 갯수
    private static final double RADIUS_KM = 10.0; //반경 10 km

    private final StoreSearchService storeSearchService;

    public List<Direction> buildDirectionList(DocumentDto documentDto) {

        if (Objects.isNull(documentDto)) return Collections.emptyList();

        return storeSearchService.searchStoreDtoList()
                .stream().map(storeDto ->
                        Direction.builder()
                                .inputAddress(documentDto.getAddressName())
                                .inputLatitude(documentDto.getLatitude())
                                .inputLongitude(documentDto.getLongitude())
                                .targetStoreName(storeDto.getStoreName())
                                .targetAddress(storeDto.getStoreAddress())
                                .targetLatitude(storeDto.getLatitude())
                                .targetLongitude(storeDto.getLongitude())
                                .distance(
                                        calculateDistance(documentDto.getLatitude(), documentDto.getLongitude(),
                                                storeDto.getLatitude(), storeDto.getLongitude())
                                )
                                .build())
                .filter(direction -> direction.getDistance() <= RADIUS_KM)
                .sorted(Comparator.comparing(Direction::getDistance))
                .limit(MAX_SEARCH_COUNT)
                .collect(Collectors.toList());

    }

    /**
     * 두 지점의 위도와 경도를 입력 받아 거리를 출력 하는 함수 (하버 사인 공식)
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371; //KM
        return earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }
}
