package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Articles;
import africa.ucj.sayMeBlog.data.models.Blog;
import africa.ucj.sayMeBlog.data.models.Comment;
import africa.ucj.sayMeBlog.data.models.User;
import africa.ucj.sayMeBlog.dtos.requests.*;
import africa.ucj.sayMeBlog.dtos.responses.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        return userService.registerUser(request);
    }

    @Override
    public String addBlogToUser(BlogRequest request) {
        Blog blog = blogService.saveBlog(request);
        User user = userService.getUser(request.getUserId());
        user.setBlog(blog);
        userService.update(user);
        return String.format("%s added to %s", blog.getBlogName(), user.getUserName());
    }

    @Override
    public void addArticleToUserBlog(ArticleRequest request) {
        var article = articleService.saveArticle(request);
        var user = userService.getUser(request.getUserId());
        var blog = user.getBlog();
        blog.getArticles().add(article);
        blogService.reSave(blog);
        userService.update(user);
    }

    @Override
    public void addCommentToArticle(CommentRequest commentRequest) {

    }

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public Blog getUserBlog(String userId) {
        return null;
    }

    @Override
    public List<Articles> getArticles(String userId) {
        return null;
    }

    @Override
    public List<Comment> getCommentsFromUserArticle(CommentRequest request) {
        return null;
    }

    @Override
    public User update(RegisterUserRequest request) {
        return null;
    }

    @Override
    public Blog updateUserBlog(BlogRequest request) {
        return null;
    }

    @Override
    public List<Articles> updateArticle(ArticleRequest request) {
        return null;
    }

    @Override
    public List<Comment> updateCommentsFromUserArticle(CommentRequest request) {
        return null;
    }

    @Override
    public String deleteUser(DeleteRequest deleteUserRequest) {
        return null;
    }

    @Override
    public String deleteBlog(DeleteRequest deleteBlogRequest) {
        return null;
    }

    @Override
    public String deleteArticle(DeleteRequest deleteArticleRequest) {
        return null;
    }

    @Override
    public String deleteComment(DeleteRequest deleteCommentRequest) {
        return null;
    }

    @Override
    public void clearDataBase() {
        userService.deleteAll();
        blogService.deleteAll();
        articleService.deleteAll();
        commentService.deleteAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @Override
    public User findUserByUsername(String username) {
        return userService.findUserByUserName(username);
    }
}
