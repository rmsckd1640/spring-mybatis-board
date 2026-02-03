package com.example.backend.mapper;

import com.example.backend.domain.Attachments;
import com.example.backend.domain.Posts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AttachmentMapperTest {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private PostMapper postMapper;

    @Test
    @DisplayName("첨부파일 삽입")
    public void insertAttachmentTest(){
        //given
        Posts post = Posts.builder()
                .title("Test").content("Test").build();

        postMapper.insertPost(post);

        Attachments attachment = Attachments.builder()
                .postId(post.getPostId()).originalName("Test.jpg").storeFileName("bee3f416-7a52-455a-885b-c8448c5b51b4").attachmentType(Attachments.AttachmentType.IMAGE).attachmentSize(2_048_000L).build();

        //when
        int result = attachmentMapper.insertAttachment(attachment);

        //then
        assertThat(result).isEqualTo(1);
        assertThat(attachment.getAttachmentId()).isNotNull();
    }

    @Test
    @DisplayName("존재하지 않는 postId에 삽입 (예외)")
    public void insertAttachmentNotFoundPostTest(){
        //given
        Attachments attachment = Attachments.builder()
                .postId(99999L).originalName("Test.jpg").storeFileName("bee3f416-7a52-455a-885b-c8448c5b51b4.jpg").attachmentType(Attachments.AttachmentType.IMAGE).attachmentSize(2_048_000L).build();

        //when & then
        assertThatThrownBy(() -> attachmentMapper.insertAttachment(attachment)).isInstanceOf(Exception.class);
    }

}

/*
1. 외래키가 없을 수 있으니 test할 때 생성
2. attachmentId가 자동 증가인데 잘 생성 되었는지 검증 해야함
3. 잘못된 postId가 들어갔을 때의 예외 테스트 해야함
 */