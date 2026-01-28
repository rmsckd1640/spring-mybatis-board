package com.example.backend.mapper;

import com.example.backend.domain.Posts;
import com.example.backend.domain.Tags;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TagMapperTest {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private PostMapper postMapper;

    @Test
//    @Rollback(false)  // 롤백 방지 --> 실제로 db에 들어가는지 확인하고 싶을 때.
    //데이터가 남으면 다음 테스트에 영향을 줄 수도 있기 때문에 테스트는 롤백하는 것이 편리하고 안전함.
    @DisplayName("게시글에 태그 insert 시 결과값 1 반환 및 id 자동 생성 확인")
    public void insertTagTest(){
        //given
        Tags tag = Tags.builder()
                .tagName("Test Tag")
                .build();

        //when
        int result = tagMapper.insertTag(tag);

        //then
        assertThat(result).isEqualTo(1);
        assertThat(tag.getTagId()).isNotNull();

    }

    @Test
    @DisplayName("존재하는 태그명 조회")
    public void findByNameTest(){
        //given
        Tags tag = Tags.builder()
                .tagName("Test")
                .build();
        tagMapper.insertTag(tag);

        //when
        Tags foundTag = tagMapper.findByName("Test");

        //then
        assertThat(foundTag).isNotNull();
        assertThat(foundTag.getTagName()).isEqualTo("Test");
    }

    @Test
    @DisplayName("존재하지 않는 태그명 조회")
    public void findByNameNotFoundTest(){
        //when
        Tags foundTag = tagMapper.findByName("없는 태그명");

        //then
        assertThat(foundTag).isNull();
    }

    @Test
    @DisplayName("중복 태그명 예외 발생 확인")
    public void insertDuplicateTagTest(){
        //given
        Tags tag1 = Tags.builder()
                .tagName("중복태그")
                .build();
        tagMapper.insertTag(tag1);

        //when & then

        Tags tag2 = Tags.builder()
                .tagName("중복태그")
                .build();
        assertThatThrownBy(() -> tagMapper.insertTag(tag2)).isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    @DisplayName("게시글-태그 관계 등록")
    public void insertPostTagTest(){
        //given
        Posts post = Posts.builder()
                .title("테스트 게시글")
                .content("테스트 내용")
                .build();
        postMapper.insertPost(post);

        Tags tag = Tags.builder()
                .tagName("Test Tag")
                .build();
        tagMapper.insertTag(tag);

        //when
        int result = tagMapper.insertPostTag(post.getPostId(), tag.getTagId());

        //then
        assertThat(result).isEqualTo(1);

    }
}