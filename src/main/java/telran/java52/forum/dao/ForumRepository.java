package telran.java52.forum.dao;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java52.forum.model.Post;

public interface ForumRepository extends MongoRepository<Post, String> {

	Stream<Post> findByAuthorIgnoreCase(String author);

	Stream<Post> findByTagsIgnoreCaseIn(Iterable<String> tags);

	Stream<Post> findByDateCreatedBetween(LocalDate start, LocalDate end);

}
