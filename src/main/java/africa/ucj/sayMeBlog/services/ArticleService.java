package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Articles;
import africa.ucj.sayMeBlog.dtos.requests.ArticleRequest;

public interface ArticleService {
    Articles saveArticle(ArticleRequest articleRequest);
    Articles getArticle(String id);
    Articles editArticle(String id, ArticleRequest articleRequest);
    String deleteArticle(String id);

    void deleteAll();
}
