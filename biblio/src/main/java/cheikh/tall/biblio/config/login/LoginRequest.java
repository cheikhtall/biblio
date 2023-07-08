package cheikh.tall.biblio.config.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}