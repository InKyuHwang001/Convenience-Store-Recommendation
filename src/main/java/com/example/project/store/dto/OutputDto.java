package com.example.project.store.dto;

import com.example.project.direction.entity.Direction;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
public class OutputDto {

    private static final String ROAD_VIEW_BASE_URL = "https://map.kakao.com/link/roadview/";
    private static final String DIRECTION_BASE_URL = "https://map.kakao.com/link/map/";

    private String storeName;
    private String storeAddress;
    private String directionUrl;
    private String roadViewUrl;
    private String distance;

    @Builder
    private OutputDto(String storeName, String storeAddress, String directionUrl, String roadViewUrl, String distance) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.directionUrl = directionUrl;
        this.roadViewUrl = roadViewUrl;
        this.distance = distance;
    }

    public static OutputDto fromEntity(Direction direction){
        String params = String.join(",", direction.getTargetStoreName(),
                String.valueOf(direction.getTargetLatitude()),
                String.valueOf(direction.getTargetLongitude()));

        String uriString = UriComponentsBuilder.fromHttpUrl(DIRECTION_BASE_URL + params)
                .toUriString();

        return OutputDto.builder()
                .storeName(direction.getTargetStoreName())
                .storeAddress(direction.getTargetAddress())
                .directionUrl(uriString)
                .roadViewUrl(
                        ROAD_VIEW_BASE_URL + direction.getTargetLatitude() + "," + direction.getTargetLongitude()
                )
                .distance(String.format("%.2f km", direction.getDistance()))
                .build();
    }

}
