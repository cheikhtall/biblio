package cheikh.tall.biblio.config.login;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "*")
public class LoginController {

    private LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(HttpServletRequest request,
                                               @RequestBody LoginRequest loginRequest)
            throws Exception {
        ResponseEntity<LoginResponse> response = loginService.login(loginRequest);

        return response;
    }
}
