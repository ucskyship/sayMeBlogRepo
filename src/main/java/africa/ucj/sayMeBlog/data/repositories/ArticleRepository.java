package africa.ucj.sayMeBlog.data.repositories;

import africa.ucj.sayMeBlog.data.models.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
}
