package africa.ucj.sayMeBlog.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Comment {
    private String id;
    private String comment;
}
