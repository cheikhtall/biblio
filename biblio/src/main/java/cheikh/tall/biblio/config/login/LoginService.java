package cheikh.tall.biblio.config.login;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class LoginService {
    RestTemplate restTemplate;
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    //@Value("${app.keycloak.login.url}")
    private String loginUrl = "http://localhost:8080/realms/biblio/protocol/openid-connect/token";
    //@Value("${app.keycloak.client-secret}")
    private String clientSecret = "GstjD1WMgW7IkSIfffFMvkgSfOP2Iips";
    //@Value("${app.keycloak.grant-type}")
    private String grantType = "password";
    //@Value("${app.keycloak.client-id}")
    private String clientId = "biblio-client";

    public ResponseEntity<LoginResponse> login (LoginRequest request){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", request.getUsername());
        map.add("password", request.getPassword());
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("grant_type", grantType);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
        ResponseEntity<LoginResponse> loginResponse = restTemplate.postForEntity(loginUrl, httpEntity, LoginResponse.class);
        return ResponseEntity.status(200).body(loginResponse.getBody());
    }
}
