package africa.ucj.sayMeBlog.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class Articles {
    private String tittle;
    private String body;

    @DBRef
    private final List<Comment> comments = new ArrayList<>();
}
