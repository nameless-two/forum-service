package telran.java52.forum.service;

import java.util.List;
import java.util.Set;

import telran.java52.forum.dto.CommentAddDto;
import telran.java52.forum.dto.PeriodDto;
import telran.java52.forum.dto.PostAddDto;
import telran.java52.forum.dto.PostDto;

public interface ForumService {

	PostDto addPost(String user, PostAddDto postAddDto);
	
	PostDto findPostById(String postId);
	
	void addLike(String postId);
	
	List<PostDto> findPostsByAuthor(String user);
	
	PostDto addComment(String postId, String user, CommentAddDto commentAddDto);
	
	PostDto deletePost(String postId);

	List<PostDto> findPostsByTags(Set<String> tags);
	
	List<PostDto> findPostsByPeriod(PeriodDto period);
	
	PostDto updatePost(String postId, PostAddDto postAddDto);
	
}
