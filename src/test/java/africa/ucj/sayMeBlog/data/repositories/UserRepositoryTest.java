package africa.ucj.sayMeBlog.data.repositories;

import africa.ucj.sayMeBlog.data.models.User;
import africa.ucj.sayMeBlog.dtos.requests.LoginUserRequest;
import africa.ucj.sayMeBlog.dtos.requests.RegisterUserRequest;
import africa.ucj.sayMeBlog.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserServiceImpl userService;

    @Test
    public void saveTest(){
        User user = new User();
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
    }

    @Test
    public void logInTest(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("ucj@gmail.com");
        request.setPassword("password");
        userService.registerUser(request);

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUserName(request.getEmail());
        loginUserRequest.setPassword(request.getPassword());
        var foundUser = userService.findUserByUserName(request.getEmail());

        assertNotNull(userRepository);
        assertNotNull(userService);
        assertEquals("ucj@gmail.com", foundUser.getUserName());
    }
}
