package com.example.project.store.dto;

import com.example.project.store.entity.Store;
import lombok.*;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class StoreDto {
    private Long id;
    private String storeName;
    private String storeAddress;
    private double latitude;
    private double longitude;

    @Builder
    private StoreDto(Long id, String storeName, String storeAddress, double latitude, double longitude) {
        this.id = id;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static StoreDto fromEntity(Store store){
        return StoreDto.builder()
                .id(store.getId())
                .storeName(store.getStoreName())
                .storeAddress(store.getStoreAddress())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .build();
    }
}
