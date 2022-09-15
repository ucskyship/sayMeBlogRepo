package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.User;
import africa.ucj.sayMeBlog.data.repositories.UserRepository;
import africa.ucj.sayMeBlog.dtos.requests.RegisterUserRequest;
import africa.ucj.sayMeBlog.dtos.responses.RegisterUserResponse;
import africa.ucj.sayMeBlog.exceptions.UserExistException;
import africa.ucj.sayMeBlog.exceptions.UserNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        for (User user : userRepository.findAll()){
            if(user.getUserName().equals(request.getEmail())){
                throw new UserExistException("User already exists, try another unique username");
            }
        }
        User user = new User();
        user.setUserName(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
     
        RegisterUserResponse res = new RegisterUserResponse();
        res.setMessage(String.format("%s registered successfully", user.getUserName()));
        return res;
    }

    @Override
    public User getUser(String id) {
        var foundUser = userRepository.findById(id);
        if (foundUser.isPresent()){
        return foundUser.get();
        }
        throw new UserNotFoundExceptions(String.format("User with %s not found", id));
    }

    @Override
    public User findUserByUserName(String email) {
        var foundUser = userRepository.findUserByUserName(email);
        if (foundUser == null){
            throw new UserNotFoundExceptions(String.format("user with %s not found", email));
        }
        return foundUser;
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUer(User user) {
        var foundUser = getUser(user.getId());
        userRepository.delete(foundUser);
    }

    @Override
    public User logIn(String id, String userName, String password) {
        var userLogIn = getUser(id);
        if (userLogIn.getUserName().equals(userName) && userLogIn.getPassword().equals(password)){
            return userLogIn;
        }
        throw new UserNotFoundExceptions(String.format("User with %s not found", id));
    }
}
