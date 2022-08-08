package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Articles;
import africa.ucj.sayMeBlog.data.repositories.ArticleRepository;
import africa.ucj.sayMeBlog.dtos.requests.ArticleRequest;
import africa.ucj.sayMeBlog.exceptions.NoArticleFound;
import africa.ucj.sayMeBlog.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Articles saveArticle(ArticleRequest articleRequest) {
        Articles article = new Articles();
        Mapper.mapRequestToArticle(articleRequest, article);
        return articleRepository.save(article);
    }

    @Override
    public Articles getArticle(String id) {
        var foundArticle = articleRepository.findById(id);
        if (foundArticle.isPresent()){
            return foundArticle.get();
        }
        throw new NoArticleFound("article not found");
    }

    @Override
    public Articles editArticle(String id, ArticleRequest articleRequest) {
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
        return null;
    }

    @Override
    public void deleteAll() {
        articleRepository.deleteAll();
    }
}
