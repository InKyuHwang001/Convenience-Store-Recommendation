package com.example.project.store.service;

import com.example.project.store.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreSearchService {

    private final StoreRepositoryService storeRepositoryService;

    public List<StoreDto> searchStoreDtoList(){
        //TODO: redis

        //db
        return storeRepositoryService.findAll()
                .stream()
                .map(StoreDto::fromEntity)
                .collect(Collectors.toList());
    }
}
