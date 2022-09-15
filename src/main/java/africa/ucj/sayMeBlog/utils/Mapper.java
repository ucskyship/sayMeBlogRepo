package africa.ucj.sayMeBlog.utils;

import africa.ucj.sayMeBlog.data.models.Article;
import africa.ucj.sayMeBlog.data.models.Blog;
import africa.ucj.sayMeBlog.data.models.Comment;
import africa.ucj.sayMeBlog.dtos.requests.ArticleRequest;
import africa.ucj.sayMeBlog.dtos.requests.BlogRequest;
import africa.ucj.sayMeBlog.dtos.requests.CommentRequest;

public class Mapper {
    public static void mapRequestToBlog(BlogRequest request, Blog blog) {
        if(request.getBlogName()!=null && request.getBlogName()!="") {
            blog.setBlogName(request.getBlogName());
        }
    }

    public static void mapRequestToArticle(ArticleRequest articleRequest, Article article) {
        article.setTittle(articleRequest.getTittle());
        article.setBody(articleRequest.getBody());
    }

    public static void mapCommentRequestToComment(CommentRequest commentRequest, Comment comment) {
        comment.setComment(comment.getComment());
    }
}
