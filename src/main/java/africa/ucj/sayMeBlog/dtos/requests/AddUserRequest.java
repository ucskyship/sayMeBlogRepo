package africa.ucj.sayMeBlog.dtos.requests;

import lombok.Data;

@Data
public class AddUserRequest {
    private String email;
    private String password;
}
