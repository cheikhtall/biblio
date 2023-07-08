package cheikh.tall.biblio.config;

import lombok.NoArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.admin.client.Keycloak;

import java.util.List;

@NoArgsConstructor
public class KeycloakConfig {

    private static Keycloak keycloak;
    @Value("${keycloak.realm}")
    public  String realm;

    public static CredentialRepresentation createCredential(String password) {
        CredentialRepresentation passwordCredential = new CredentialRepresentation();
        passwordCredential.setTemporary(false);
        passwordCredential.setType(CredentialRepresentation.PASSWORD);
        passwordCredential.setValue(password);
        return passwordCredential;
    }

    public  Keycloak getInstance(){
        return keycloak=  KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080")
                .realm("master")
                .username("admin")
                .password("passer123")
                .clientId("admin-cli")
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    }

    public void addRoles(String roleName,String description){
        RoleRepresentation role=new RoleRepresentation();
        role.setName(roleName);
        role.setDescription(description);
        role.setComposite(true);
        keycloak.realm("biblio").roles().create(role);
    }

    public void addRoleToUser(String username,String myRole ){
        List<RoleRepresentation> roles=keycloak.realm("biblio").roles().list();

        UsersResource users=keycloak.realm("biblio").users();

        UserRepresentation user=users.search(username).get(0);
        RoleRepresentation role=new RoleRepresentation();

        for(RoleRepresentation r:roles){
            if(r.getName().equals(myRole)){
                role=r;
            }
        }
        users.get(user.getId()).roles().realmLevel().add(List.of(role));
    }

}
