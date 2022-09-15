package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Article;
import africa.ucj.sayMeBlog.data.models.Blog;
import africa.ucj.sayMeBlog.data.models.Comment;
import africa.ucj.sayMeBlog.data.models.User;
import africa.ucj.sayMeBlog.dtos.requests.*;
import africa.ucj.sayMeBlog.dtos.responses.RegisterUserResponse;
import africa.ucj.sayMeBlog.exceptions.CommentNotFoundException;

import java.util.List;

public interface ApplicationService {
    RegisterUserResponse registerUser(RegisterUserRequest request);
    String addBlogToUser(BlogRequest request);
    void addArticleToUserBlog(ArticleRequest request);
    void addCommentToArticle(CommentRequest commentRequest);

    User getUser(String id);
    Blog getUserBlog(String userId);
    List<Article> getAllArticles(String userId);
    List<Comment> getCommentsFromUserArticle(String articleId);

    void update(RegisterUserRequest request);
    void updateUserBlog(BlogRequest request);
    void updateArticle(ArticleRequest request);
    void updateCommentsFromUserArticle(CommentRequest request) throws CommentNotFoundException;

    String deleteUser(DeleteRequest deleteUserRequest);
    String deleteBlog(DeleteRequest deleteBlogRequest);
    String deleteArticle(DeleteRequest deleteArticleRequest);
    String deleteComment(DeleteRequest deleteCommentRequest) throws CommentNotFoundException;

    void clearDataBase();

    List<User> getAllUsers();

    User findUserByUsername(String userName);

    Article getAnArticle(String tittle, String id);
}
