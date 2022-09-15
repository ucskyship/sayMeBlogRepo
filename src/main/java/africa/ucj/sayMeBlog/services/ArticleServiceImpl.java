package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Article;
import africa.ucj.sayMeBlog.data.models.Comment;
import africa.ucj.sayMeBlog.data.repositories.ArticleRepository;
import africa.ucj.sayMeBlog.data.repositories.CommentRepository;
import africa.ucj.sayMeBlog.dtos.requests.ArticleRequest;
import africa.ucj.sayMeBlog.exceptions.NoArticleFound;
import africa.ucj.sayMeBlog.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Article saveArticle(ArticleRequest articleRequest) {
        Article article = new Article();
        Mapper.mapRequestToArticle(articleRequest, article);
        return articleRepository.save(article);
    }

    @Override
    public Article getArticle(String id) {
        var foundArticle = articleRepository.findById(id);
        if (foundArticle.isPresent()){
            return foundArticle.get();
        }
        throw new NoArticleFound("article not found");
    }

    @Override
    public Article editArticle(String id, ArticleRequest articleRequest) {
        var foundArticle = articleRepository.findById(id);
        if(foundArticle.isPresent()){
            Mapper.mapRequestToArticle(articleRequest, foundArticle.get());
            articleRepository.save(foundArticle.get());
            return foundArticle.get();
        }
        throw new NoArticleFound("article not found");
    }

    @Override
    public String deleteArticle(String id) {
        var foundArticle = articleRepository.findById(id);
        if(foundArticle.isPresent()){
            articleRepository.delete(foundArticle.get());
            return "article deleted";
        }
        return "unsuccessful";
    }

    @Override
    public void deleteAll() {
        articleRepository.deleteAll();
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public List<Comment> getComment(String articleId) {
        var foundArticle = articleRepository.findById(articleId);
        return foundArticle.map(Article::getComments).orElseThrow(()-> new NoArticleFound("article not found"));
    }

}
