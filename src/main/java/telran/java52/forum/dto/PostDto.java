package telran.java52.forum.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
    String id;
    String title;
    String content;
    String author;
    LocalDateTime dateCreated;
    String[] tags;
    int likes;
    CommentDto[] comments;

}
