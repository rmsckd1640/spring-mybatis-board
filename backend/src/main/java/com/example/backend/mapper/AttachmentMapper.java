package com.example.backend.mapper;

import com.example.backend.domain.Attachments;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachmentMapper {
    int insertAttachment (Attachments attachments);
}
