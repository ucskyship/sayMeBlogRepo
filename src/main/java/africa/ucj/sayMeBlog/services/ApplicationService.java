package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Articles;
import africa.ucj.sayMeBlog.data.models.Blog;
import africa.ucj.sayMeBlog.data.models.Comment;
import africa.ucj.sayMeBlog.data.models.User;
import africa.ucj.sayMeBlog.dtos.requests.*;
import africa.ucj.sayMeBlog.dtos.responses.RegisterUserResponse;

import java.util.Collection;
import java.util.List;

public interface ApplicationService {
    RegisterUserResponse registerUser(RegisterUserRequest request);
    String addBlogToUser(BlogRequest request);
    void addArticleToUserBlog(ArticleRequest request);
    void addCommentToArticle(CommentRequest commentRequest);

    User getUser(String id);
    Blog getUserBlog(String userId);
    List<Articles> getArticles(String userId);
    List<Comment> getCommentsFromUserArticle(CommentRequest request);

    User update(RegisterUserRequest request);
    Blog updateUserBlog(BlogRequest request);
    List<Articles> updateArticle(ArticleRequest request);
    List<Comment> updateCommentsFromUserArticle(CommentRequest request);

    String deleteUser(DeleteRequest deleteUserRequest);
    String deleteBlog(DeleteRequest deleteBlogRequest);
    String deleteArticle(DeleteRequest deleteArticleRequest);
    String deleteComment(DeleteRequest deleteCommentRequest);

    void clearDataBase();

    List<User> getAllUsers();

    User findUserByUsername(String ucj);
}
