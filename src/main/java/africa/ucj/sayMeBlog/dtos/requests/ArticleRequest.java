package africa.ucj.sayMeBlog.dtos.requests;

import lombok.Data;

@Data
public class ArticleRequest{
    private String userId;
    private String tittle;
    private String body;
    private String articleId;
}
