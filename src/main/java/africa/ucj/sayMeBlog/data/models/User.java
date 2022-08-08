package africa.ucj.sayMeBlog.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class User {
    @Id
    private String id;
    private String userName;
    private String password;

    @DBRef
    private Blog blog;
}
