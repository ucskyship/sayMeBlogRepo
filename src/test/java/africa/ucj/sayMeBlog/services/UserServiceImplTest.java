package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.repositories.UserRepository;
import africa.ucj.sayMeBlog.dtos.requests.RegisterUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void registerUser_repositorySizeIsOneTest(){
//        given that I have a register form
//        when I try to register
//        check that repository size have increased

        RegisterUserRequest userRequestForm = new RegisterUserRequest();
        userRequestForm.setEmail("test");
        userRequestForm.setPassword("test");
        userService.registerUser(userRequestForm);

        assertEquals(1L, userRepository.count());
    }
}