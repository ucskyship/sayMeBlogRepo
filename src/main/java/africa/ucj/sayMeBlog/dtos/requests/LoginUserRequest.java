package africa.ucj.sayMeBlog.dtos.requests;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String userName;
    private String password;
}
