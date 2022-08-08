package africa.ucj.sayMeBlog.dtos.requests;

import lombok.Data;

@Data
public class BlogRequest {
    private String userId;
    private String blogName;
}
