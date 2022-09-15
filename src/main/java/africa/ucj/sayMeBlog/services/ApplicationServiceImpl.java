package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Article;
import africa.ucj.sayMeBlog.data.models.Blog;
import africa.ucj.sayMeBlog.data.models.Comment;
import africa.ucj.sayMeBlog.data.models.User;
import africa.ucj.sayMeBlog.dtos.requests.*;
import africa.ucj.sayMeBlog.dtos.responses.RegisterUserResponse;
import africa.ucj.sayMeBlog.exceptions.CommentNotFoundException;
import africa.ucj.sayMeBlog.exceptions.NoArticleFound;
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
        blog = blogService.reSave(blog);
        user.setBlog(blog);
        userService.update(user);
    }

    @Override
    public void addCommentToArticle(CommentRequest commentRequest) {
        var user = userService.getUser(commentRequest.getUserId());
        var comment = commentService.save(commentRequest);
        for (var article: user.getBlog().getArticles()){
            if (article.getId().equals(commentRequest.getArticleId())){
                article.getComments().add(comment);
                articleService.updateArticle(article);
                return;
            }
        }
    }

    @Override
    public User getUser(String id) {
        return userService.getUser(id);
    }

    @Override
    public Blog getUserBlog(String userId) {
        return userService.getUser(userId).getBlog();
    }

    @Override
    public List<Article> getAllArticles(String userId) {
        var foundUser = userService.getUser(userId);
        return foundUser.getBlog().getArticles();
    }

    @Override
    public List<Comment> getCommentsFromUserArticle(String articleId) {
        return articleService.getComment(articleId);
    }

    @Override
    public void update(RegisterUserRequest request) {
        var foundUser = getUser(request.getUserId());
        foundUser.setUserName(request.getEmail());
        foundUser.setPassword(request.getPassword());
        userService.update(foundUser);
    }

    @Override
    public void updateUserBlog(BlogRequest request) {
        getUser(request.getUserId());
        var foundBlog = getUserBlog(request.getBlogName());
        foundBlog.setBlogName(request.getBlogName());
        blogService.reSave(foundBlog);
    }

    @Override
    public void updateArticle(ArticleRequest request) {
        getUser(request.getUserId());
        var foundArticle = articleService.getArticle(request.getArticleId());
        foundArticle.setTittle(request.getTittle());
        foundArticle.setBody(request.getBody());
        articleService.updateArticle(foundArticle);
    }

    @Override
    public void updateCommentsFromUserArticle(CommentRequest request) throws CommentNotFoundException {
        getUser(request.getUserId());
        articleService.getArticle(request.getArticleId());
        var foundComment = commentService.getComment(request.getCommentId());
        foundComment.setComment(request.getCommentBody());
        commentService.updateComment(foundComment);
    }

    @Override
    public String deleteUser(DeleteRequest deleteUserRequest) {
        userService.deleteUer(getUser(deleteUserRequest.getUserId()));
        return "successful";
    }

    @Override
    public String deleteBlog(DeleteRequest deleteBlogRequest) {
        var foundUser = getUser(deleteBlogRequest.getUserId());
        var userBlog = foundUser.getBlog();
        blogService.deleteBlog(userBlog.getId());
        return "deleted";
    }

    @Override
    public String deleteArticle(DeleteRequest deleteArticleRequest) {
//        var foundUser = getUser(deleteArticleRequest.getUserId());
//        var foundBlog = getUserBlog(foundUser.getId());
        var foundArticle = articleService.getArticle(deleteArticleRequest.getArticleId());
        articleService.deleteArticle(foundArticle.getId());
        return "deleted successful";
    }

    @Override
    public String deleteComment(DeleteRequest deleteCommentRequest) throws CommentNotFoundException {
        commentService.getComment(deleteCommentRequest.getCommentId());
        return "successful";
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

    @Override
    public Article getAnArticle(String tittle, String id) {
        var user = userService.getUser(id);
        for (var article:user.getBlog().getArticles()){
            if(article.getTittle().equals(tittle)){
                return article;
            }
        }
        throw new NoArticleFound("not found");

    }
}
