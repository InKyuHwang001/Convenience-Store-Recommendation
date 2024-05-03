package com.example.project.store.repository;

import com.example.project.store.entity.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;

    @BeforeEach
    void setup() {
        storeRepository.deleteAll();
    }

    @Test
    @DisplayName("")
    void saveTest() {

        //given
        String address = "경기도 군포시 수리산로";
        String name = "약국";
        double latitude = 36.11;
        double longitude = 128.11;


        Store store = Store.builder()
                .storeAddress(address)
                .storeName(name)
                .longitude(longitude)
                .latitude(latitude)
                .build();
        //when
        Store entity = storeRepository.save(store);

        //then
        assertEquals(entity.getLatitude(), latitude);
        assertEquals(entity.getStoreName(), name);
        assertEquals(entity.getStoreAddress(), address);
        assertEquals(entity.getLongitude(), longitude);
    }
}