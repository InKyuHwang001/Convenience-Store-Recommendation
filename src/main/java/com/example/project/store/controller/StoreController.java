package com.example.project.store.controller;

import com.example.project.store.dto.InputDto;
import com.example.project.store.dto.OutputDto;
import com.example.project.store.service.StoreRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreRecommendationService storeRecommendationService;


    @PostMapping("/search")
    public List<OutputDto> search(InputDto inputDto){
        return  storeRecommendationService.recommendationStoreList(inputDto);
    }
}
