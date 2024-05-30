package telran.java52.security;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java52.forum.dao.ForumRepository;
import telran.java52.forum.model.Post;

@Service
@RequiredArgsConstructor
public class CustomWebSecurity {

    final ForumRepository forumRepository;
    
    public boolean checkPostAuthor(String postId, String author) {
        Post post = forumRepository.findById(postId).orElse(null);
        return  post != null && post.getAuthor().equalsIgnoreCase(author);
    }
}
