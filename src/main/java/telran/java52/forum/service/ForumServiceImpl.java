package telran.java52.forum.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java52.forum.dto.PeriodDto;
import telran.java52.forum.dto.PostAddDto;
import telran.java52.forum.dto.PostDto;
import telran.java52.forum.dto.PostUpdateDto;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {

	@Override
	public PostDto addPost(String user, PostAddDto postAddDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto findPostById(String postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLike(String postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> findPostsByAuthor(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto addComment(String postId, String user, String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto deletePost(String postId) {
		// TODO Auto-generated method stub
		return null;
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
	public PostDto updatePost(String postId, PostUpdateDto postUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
