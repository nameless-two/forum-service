package telran.java52.forum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java52.forum.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
	

}
