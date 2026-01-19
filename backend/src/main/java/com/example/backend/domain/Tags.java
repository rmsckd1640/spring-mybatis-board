package com.example.backend.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Tags {
    private Long tagId;
    private String tagName;
}
