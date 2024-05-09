package telran.java52.forum.service;

import java.util.Set;

import telran.java52.forum.dto.CommentAddDto;
import telran.java52.forum.dto.PeriodDto;
import telran.java52.forum.dto.PostAddDto;
import telran.java52.forum.dto.PostDto;

public interface ForumService {

	PostDto addPost(String user, PostAddDto postAddDto);
	
	PostDto findPostById(String postId);
	
	void addLike(String postId);
	
	Iterable<PostDto> findPostsByAuthor(String user);
	
	PostDto addComment(String postId, String user, CommentAddDto commentAddDto);
	
	PostDto deletePost(String postId);

	Iterable<PostDto> findPostsByTags(Set<String> tags);
	
	Iterable<PostDto> findPostsByPeriod(PeriodDto period);
	
	PostDto updatePost(String postId, PostAddDto postAddDto);
	
}
