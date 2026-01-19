package com.example.backend.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Attachments {
    private Long attachmentId;
    private Long postId;
    private String originalName;
    private String storeFileName;
    private AttachmentType attachmentType;
    private Long attachmentSize;

    public enum AttachmentType {
        IMAGE,
        ETC
    }
}
