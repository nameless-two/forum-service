package telran.java52.forum.service;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java52.forum.dao.ForumRepository;
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

	final ForumRepository forumRepository;
	final ModelMapper modelMapper;

	@Override
	public PostDto addPost(String user, PostAddDto postAddDto) {
		Post post = modelMapper.map(postAddDto, Post.class);
		post.setAuthor(user);
		post = forumRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPostById(String postId) {
		return modelMapper.map(forumRepository.findById(postId).orElseThrow(PostNotFoundException::new), PostDto.class);
	}

	@Override
	public void addLike(String postId) {
		Post post = forumRepository.findById(postId).orElseThrow(PostNotFoundException::new);
		post.addLike();
		forumRepository.save(post);
	}

	@Override
	public PostDto addComment(String postId, String user, CommentAddDto commentAddDto) {
		Post post = forumRepository.findById(postId).orElseThrow(PostNotFoundException::new);
		Comment comment = new Comment(user, commentAddDto.getMessage());
		post.addComment(comment);
		post = forumRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto deletePost(String postId) {
		PostDto post = findPostById(postId);
		forumRepository.deleteById(postId);
		return post;
	}

	@Override
	public List<PostDto> findPostsByAuthor(String user) {
		return forumRepository.findByAuthorIgnoreCase(user).map(p -> modelMapper.map(p, PostDto.class)).toList();
	}

	@Override
	public List<PostDto> findPostsByTags(Set<String> tags) {
		return forumRepository.findByTagsIgnoreCaseIn(tags).map(p -> modelMapper.map(p, PostDto.class)).toList();
	}

	@Override
	public List<PostDto> findPostsByPeriod(PeriodDto period) {
		return forumRepository.findByDateCreatedBetween(period.getDateFrom(), period.getDateTo())
				.map(p -> modelMapper.map(p, PostDto.class))
				.toList();
	}

	@Override
	public PostDto updatePost(String postId, PostAddDto postAddDto) {
		Post post = forumRepository.findById(postId).orElseThrow(PostNotFoundException::new);
		if (postAddDto.getTitle() != null)
			post.setTitle(postAddDto.getTitle());
		if (postAddDto.getContent() != null)
			post.setContent(postAddDto.getContent());
		List<String> tags = postAddDto.getTags();
		if (tags != null)
			tags.forEach(post::addTag);
		post = forumRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

}
