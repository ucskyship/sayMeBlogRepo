package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.User;
import africa.ucj.sayMeBlog.dtos.requests.BlogRequest;
import africa.ucj.sayMeBlog.dtos.requests.RegisterUserRequest;
import africa.ucj.sayMeBlog.exceptions.UserExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationServiceImplTest {
    @Autowired
    ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        applicationService.clearDataBase();
    }

    @Test
    void registerUser() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("hfhf");
        request.setPassword("hfhf");
        applicationService.registerUser(request);

        assertThrows(UserExistException.class, () -> applicationService.registerUser(request));
        assertEquals(1, applicationService.getAllUsers().size());
    }

    @Test
    void addBlogToUser() {
        //register user
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("ucj");
        request.setPassword("hfhf");
        applicationService.registerUser(request);
        //finds user
        User user = applicationService.findUserByUsername("ucj");
        assertNotNull(user);
        assertNull(user.getBlog());


        //adds blog to usr
        BlogRequest blogRequest = new BlogRequest();
        blogRequest.setUserId(user.getId());
        blogRequest.setBlogName("my test blog");
        applicationService.addBlogToUser(blogRequest);

        user = applicationService.findUserByUsername("ucj");

        assertNotNull(user.getBlog());
        assertEquals( "my test blog", user.getBlog().getBlogName());

    }

    @Test
    void addArticleToUserBlog() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("ucj");
        request.setPassword("hfhf");
        applicationService.registerUser(request);
    }

    @Test
    void addCommentToArticle() {
    }

    @Test
    void getUser() {
    }

    @Test
    void getUserBlog() {
    }

    @Test
    void getArticles() {
    }

    @Test
    void getCommentsFromUserArticle() {
    }

    @Test
    void update() {
    }

    @Test
    void updateUserBlog() {
    }

    @Test
    void updateArticle() {
    }

    @Test
    void updateCommentsFromUserArticle() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void deleteBlog() {
    }

    @Test
    void deleteArticle() {
    }

    @Test
    void deleteComment() {
    }
}