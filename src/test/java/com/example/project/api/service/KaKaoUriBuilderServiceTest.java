package com.example.project.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KaKaoUriBuilderServiceTest {

    @Autowired
    private KaKaoUriBuilderService kaKaoUriBuilderService;



    @Test
    @DisplayName("buildUriByAddressSearch [성공]")
    void buildUriByAddressSearchSuccess(){
        //given
        String address = "경기도 군포시";

        //when
        URI uri = kaKaoUriBuilderService.buildUriByAddressSearch(address);
        String decoded = URLDecoder.decode(uri.toString(), UTF_8);

        //then
        assertEquals(decoded,  "https://dapi.kakao.com/v2/local/search/address.json?query=경기도 군포시");

    }
}