package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.User;
import africa.ucj.sayMeBlog.dtos.requests.RegisterUserRequest;
import africa.ucj.sayMeBlog.dtos.responses.RegisterUserResponse;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);
    User getUser(String id);
    User logIn(String id, String userName, String password);
    User findUserByUserName(String username);

    void update(User user);

    void deleteAll();

    List<User> findAllUsers();

    void deleteUer(User user);
}
