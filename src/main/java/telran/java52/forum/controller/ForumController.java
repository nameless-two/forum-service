package telran.java52.forum.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java52.forum.dto.CommentAddDto;
import telran.java52.forum.dto.PeriodDto;
import telran.java52.forum.dto.PostAddDto;
import telran.java52.forum.dto.PostDto;
import telran.java52.forum.service.ForumService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class ForumController {

	final ForumService forumService;

	@PostMapping("/post/{user}")
	public PostDto addPost(@PathVariable String user, @RequestBody PostAddDto postAddDto) {
		return forumService.addPost(user, postAddDto);
	}

	@GetMapping("/post/{postId}")
	public PostDto findPostById(@PathVariable String postId) {
		return forumService.findPostById(postId);
	}

	@PutMapping("/post/{postId}/like")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addLike(@PathVariable String postId) {
		forumService.addLike(postId);
	}

	@GetMapping("/posts/author/{user}")
	public Iterable<PostDto> findPostsByAuthor(@PathVariable String user) {
		return forumService.findPostsByAuthor(user);
	}

	@PutMapping("/post/{postId}/comment/{user}")
	public PostDto addComment(@PathVariable String postId, @PathVariable String user,
			@RequestBody CommentAddDto commentAddDto) {
		return forumService.addComment(postId, user, commentAddDto);
	}

	@DeleteMapping("/post/{postId}")
	public PostDto deletePost(@PathVariable String postId) {
		return forumService.deletePost(postId);
	}

	@PostMapping("/posts/tags")
	public Iterable<PostDto> findPostsByTags(@RequestBody Set<String> tags) {
		return forumService.findPostsByTags(tags);
	}

	@PostMapping("/posts/period")
	public Iterable<PostDto> findPostsByPeriod(@RequestBody PeriodDto period) {
		return forumService.findPostsByPeriod(period);
	}

	@PutMapping("post/{postId}")
	public PostDto updatePost(@PathVariable String postId, @RequestBody PostAddDto postAddDto) {
		return forumService.updatePost(postId, postAddDto);
	}

}
