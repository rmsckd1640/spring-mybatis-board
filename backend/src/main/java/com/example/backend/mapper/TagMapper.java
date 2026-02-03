package com.example.backend.mapper;

import com.example.backend.domain.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TagMapper {
    int insertTag(Tags tags);

    Tags findByName(String tagName);

    //Param --> MyBatis에서 메서드 파라미터를 XML의 변수명과 매핑하는 어노테이션.
    //파라미터가 2개 이상일 때 권장 (가독성 좋음)
    //예를들어 #{param1}으로 자동 생성되는걸 #{postId} 이렇게 명시할 수 있음.
    int insertPostTag(
            @Param("postId") Long postId,
            @Param("tagId") Long tagId
    );
}
