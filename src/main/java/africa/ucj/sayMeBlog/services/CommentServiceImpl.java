package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Comment;
import africa.ucj.sayMeBlog.data.repositories.CommentRepository;
import africa.ucj.sayMeBlog.dtos.requests.CommentRequest;
import africa.ucj.sayMeBlog.exceptions.CommentNotFoundException;
import africa.ucj.sayMeBlog.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Override
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment save(CommentRequest commentRequest) {
        Comment comment = new Comment();
        Mapper.mapCommentRequestToComment(commentRequest,comment);
        return commentRepository.save(comment);
    }

    @Override
    public Comment getComment(String commentId) throws CommentNotFoundException {
        var foundComment = commentRepository.findById(commentId);
        if(foundComment.isPresent()){
            return foundComment.get();
        }
        throw new CommentNotFoundException("comment with " + commentId + " not found");
    }

    @Override
    public void deleteAll() {
        commentRepository.deleteAll();
    }
}
