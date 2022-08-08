package africa.ucj.sayMeBlog.dtos.requests;

import lombok.Data;

@Data
public class CommentRequest {
    private String userId;
    private String articleId;

    private String commentBody;
    private String commentId;
}
