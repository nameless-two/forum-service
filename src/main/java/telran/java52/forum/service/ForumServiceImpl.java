package telran.java52.forum.service;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java52.forum.dao.PostRepository;
import telran.java52.forum.dto.CommentAddDto;
import telran.java52.forum.dto.PeriodDto;
import telran.java52.forum.dto.PostAddDto;
import telran.java52.forum.dto.PostDto;
import telran.java52.forum.dto.exception.PostNotFoundException;
import telran.java52.forum.model.Comment;
import telran.java52.forum.model.Post;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {

	final PostRepository postRepository;
	final ModelMapper modelMapper;

	@Override
	public PostDto addPost(String user, PostAddDto postAddDto) {
		Post post = modelMapper.map(postAddDto, Post.class);
		post.setAuthor(user);
		post = postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPostById(String postId) {
		return modelMapper.map(postRepository.findById(postId).orElseThrow(PostNotFoundException::new), PostDto.class);
	}

	@Override
	public void addLike(String postId) {
		Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
		post.addLike();
		postRepository.save(post);
	}

	@Override
	public List<PostDto> findPostsByAuthor(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto addComment(String postId, String user, CommentAddDto commentAddDto) {
		Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
		Comment comment = new Comment(user, commentAddDto.getMessage());
		post.addComment(comment);
		post = postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto deletePost(String postId) {
		PostDto post = findPostById(postId);
		postRepository.deleteById(postId);
		return post;
	}

	@Override
	public List<PostDto> findPostsByTags(Set<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> findPostsByPeriod(PeriodDto period) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto updatePost(String postId, PostAddDto postAddDto) {
		Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
		if (postAddDto.getTitle() != null)
			post.setTitle(postAddDto.getTitle());
		if (postAddDto.getContent() != null)
			post.setContent(postAddDto.getContent());
		List<String> tags = postAddDto.getTags();
		if (tags != null)
			tags.forEach(post::addTag);
		post = postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

}
