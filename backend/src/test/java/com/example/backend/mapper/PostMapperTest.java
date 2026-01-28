package com.example.backend.mapper;

import com.example.backend.domain.Posts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest //자동 롤백 활성화
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostMapperTest {

    @Autowired
    private PostMapper postMapper;

    @Test
    @DisplayName("게시글 insert 시 결과값 1 반환 및 id 자동 생성 확인")
    public void insertPostTest(){
        //given
        Posts post = Posts.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .build();

        //when
        int result = postMapper.insertPost(post);

        //then
        assertThat(result).isEqualTo(1);
        assertThat(post.getPostId()).isNotNull(); //자동 생성된 id가 정상적으로 할당되었는지 확인
    }
}
