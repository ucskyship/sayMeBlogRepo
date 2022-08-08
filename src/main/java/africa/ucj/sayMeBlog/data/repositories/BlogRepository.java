package africa.ucj.sayMeBlog.data.repositories;

import africa.ucj.sayMeBlog.data.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
}
