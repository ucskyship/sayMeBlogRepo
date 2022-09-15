package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Article;
import africa.ucj.sayMeBlog.data.models.Comment;
import africa.ucj.sayMeBlog.dtos.requests.ArticleRequest;

import java.util.List;

public interface ArticleService {
    Article saveArticle(ArticleRequest articleRequest);
    Article getArticle(String id);
    Article editArticle(String id, ArticleRequest articleRequest);
    String deleteArticle(String id);
    void deleteAll();
    void updateArticle(Article article);
    List<Comment> getComment(String articleId);
}
