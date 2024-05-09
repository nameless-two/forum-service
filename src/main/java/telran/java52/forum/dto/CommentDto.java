package telran.java52.forum.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	String user;
	String message;
	LocalDateTime dateCreated;
	Integer likes;

}
