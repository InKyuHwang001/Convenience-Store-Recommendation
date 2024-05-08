package com.example.project.store.dto;

import lombok.Builder;
import lombok.Getter;

@Getter

public class InputDto {

    private String address;

    @Builder
    public InputDto(String address) {
        this.address = address;
    }
}
