package africa.ucj.sayMeBlog.dtos.requests;

import lombok.Data;

@Data
public class DeleteRequest {
    private String userId;
    private String articleId;
    private String commentId;
}
