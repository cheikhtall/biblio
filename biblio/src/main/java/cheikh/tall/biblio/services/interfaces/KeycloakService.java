package cheikh.tall.biblio.services.interfaces;

import cheikh.tall.biblio.dtos.appuser.AppUserRequestDto;
import cheikh.tall.biblio.entities.AppUser;

import javax.ws.rs.core.Response;
import java.util.Optional;


public interface KeycloakService {
    Response addUser(AppUserRequestDto appUserRequestDto);
    void updateUser(AppUserRequestDto appUserRequestDto);

    void addRoleToUser(String username, String rolename);
    void deleteUser(Optional<AppUser> appUser);

}