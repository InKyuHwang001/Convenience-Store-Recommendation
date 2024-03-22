package com.example.project.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MetaDto {

    @JsonProperty("total_count")
    private Integer totalCount;
}
