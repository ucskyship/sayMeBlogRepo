package africa.ucj.sayMeBlog.dtos.requests;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String userId;
    private String email;
    private String password;
}
