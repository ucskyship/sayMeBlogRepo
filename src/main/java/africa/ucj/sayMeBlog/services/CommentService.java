package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Comment;
import africa.ucj.sayMeBlog.dtos.requests.CommentRequest;
import africa.ucj.sayMeBlog.exceptions.CommentNotFoundException;

public interface CommentService {
    Comment save(CommentRequest commentRequest);
    Comment getComment(String commentId) throws CommentNotFoundException;
    void updateComment(Comment comment);
    void deleteAll();
}
