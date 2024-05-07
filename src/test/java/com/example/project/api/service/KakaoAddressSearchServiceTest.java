package com.example.project.api.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KakaoAddressSearchServiceTest {

    @Autowired
    private KakaoAddressSearchService kakaoAddressSearchService;

    @Test
    @DisplayName("requestAddressSearch [성공]")
    void requestAddressSearchSuccess(){
        // given
        String address = "경기도 군포시";
        //when
        var result = kakaoAddressSearchService.requestAddressSearch(address);

        //then
        assertFalse(result.getDocumentList().isEmpty());
        assertTrue(result.getMetaDto().getTotalCount() > 0);
        assertNotNull(result.getDocumentList().get(0).getAddressName());

    }
    @Test
    @DisplayName("requestAddressSearch [실패]: address = null -> null 반환")
    void requestAddressSearchFail(){
        // given
        String address = null;
        //when
        var result = kakaoAddressSearchService.requestAddressSearch(address);
        //then
        assertNull(result);

    }

    @Test
    @DisplayName("")
    void test (){
        //given

        //when

        //then

    }
}