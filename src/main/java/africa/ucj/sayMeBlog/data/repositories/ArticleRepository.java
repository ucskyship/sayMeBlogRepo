package africa.ucj.sayMeBlog.data.repositories;

import africa.ucj.sayMeBlog.data.models.Articles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Articles, String> {
}
