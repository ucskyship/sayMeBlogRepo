package africa.ucj.sayMeBlog.data.repositories;

import africa.ucj.sayMeBlog.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUserName(String username);
}
