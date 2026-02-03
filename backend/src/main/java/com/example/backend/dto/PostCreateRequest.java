package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class PostCreateRequest {
    @NotBlank //null, 빈칸 막음
    private String title;

    @NotBlank
    private String content;

    private List<String> tagNames;

    //파일은 MultipartFile로 별도 처리
}
