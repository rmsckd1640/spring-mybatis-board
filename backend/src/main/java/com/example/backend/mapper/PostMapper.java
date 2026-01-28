package com.example.backend.mapper;

import com.example.backend.domain.Posts;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    //새로 삽입된 행의 개수 반환
    int insertPost(Posts posts);
}
