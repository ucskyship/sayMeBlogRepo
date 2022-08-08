package africa.ucj.sayMeBlog.utils;

import africa.ucj.sayMeBlog.data.models.Articles;
import africa.ucj.sayMeBlog.data.models.Blog;
import africa.ucj.sayMeBlog.data.repositories.ArticleRepository;
import africa.ucj.sayMeBlog.dtos.requests.ArticleRequest;
import africa.ucj.sayMeBlog.dtos.requests.BlogRequest;

public class Mapper {
    public static void mapRequestToBlog(BlogRequest request, Blog blog) {
        if(request.getBlogName()!=null && request.getBlogName()!="") {
            blog.setBlogName(request.getBlogName());
        }
    }

    public static void mapRequestToArticle(ArticleRequest articleRequest, Articles article) {
        article.setTittle(articleRequest.getTittle());
        article.setBody(articleRequest.getBody());
    }
}
