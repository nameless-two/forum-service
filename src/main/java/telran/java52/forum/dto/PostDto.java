package telran.java52.forum.dto;

import java.time.LocalDateTime;
import java.util.List;

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
    List<String> tags;
    int likes;
    List<CommentDto> comments;

}
