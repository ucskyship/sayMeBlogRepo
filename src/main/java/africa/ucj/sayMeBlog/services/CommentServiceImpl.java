package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Override
    public void deleteAll() {

    }
}
