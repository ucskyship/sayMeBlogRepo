package africa.ucj.sayMeBlog.controllers;

import africa.ucj.sayMeBlog.dtos.requests.ArticleRequest;
import africa.ucj.sayMeBlog.dtos.requests.BlogRequest;
import africa.ucj.sayMeBlog.dtos.requests.RegisterUserRequest;
import africa.ucj.sayMeBlog.dtos.responses.RegisterUserResponse;
import africa.ucj.sayMeBlog.exceptions.BlogNotFoundException;
import africa.ucj.sayMeBlog.exceptions.NoArticleFound;
import africa.ucj.sayMeBlog.exceptions.UserExistException;
import africa.ucj.sayMeBlog.exceptions.UserNotFoundExceptions;
import africa.ucj.sayMeBlog.services.ArticleService;
import africa.ucj.sayMeBlog.services.BlogService;
import africa.ucj.sayMeBlog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private ArticleService articleService;


    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            RegisterUserResponse serviceResponse = userService.registerUser(registerUserRequest);
            return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
        } catch (UserExistException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(er.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> findUserByUserName(@PathVariable String email) {
        try {
            return new ResponseEntity<>(userService.findUserByUserName(email), HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(er.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/userName")
    public ResponseEntity<?> logInUser(@PathVariable String id, @RequestParam String userName, @RequestParam String password) {
        try {
            var loginDetails = userService.logIn(id, userName, password);
            return new ResponseEntity<>(loginDetails, HttpStatus.OK);
        } catch (UserNotFoundExceptions err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/blog")
    public ResponseEntity<?> addBlog(@RequestBody BlogRequest blogRequest) {
        try {
            return new ResponseEntity<>(blogService.saveBlog(blogRequest), HttpStatus.OK);
        } catch (Exception r) {
            return new ResponseEntity<>(r.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<?> getBlog(@PathVariable String id) {
        try {
            return new ResponseEntity<>(blogService.getBlog(id), HttpStatus.FOUND);
        } catch (BlogNotFoundException er) {
            return new ResponseEntity<>(er.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/blog/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable String id, BlogRequest blogRequest) {
        try {
            return new ResponseEntity<>(blogService.updateBlog(id, blogRequest), HttpStatus.CONTINUE);
        } catch (Exception r) {
            return new ResponseEntity<>(r.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable String id) {
        try {
            return new ResponseEntity<>(blogService.deleteBlog(id), HttpStatus.GONE);
        } catch (Exception rr) {
            return new ResponseEntity<>(rr.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/article")
    public ResponseEntity<?> addArticle(@RequestBody ArticleRequest request) {
        try {
            return new ResponseEntity<>(articleService.saveArticle(request), HttpStatus.OK);
        } catch (Exception rr) {
            return new ResponseEntity<>(rr.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/article{id}")
    public ResponseEntity<?> updateArticle(@PathVariable String id, ArticleRequest request) {

        try {
            return new ResponseEntity<>(articleService.editArticle(id, request), HttpStatus.OK);
        } catch (NoArticleFound err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/article{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable String id) {
        try {
            return new ResponseEntity<>(articleService.deleteArticle(id), HttpStatus.OK);
        } catch (NoArticleFound rr) {
            return new ResponseEntity<>(rr.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
