package cheikh.tall.biblio.services.impl;

import cheikh.tall.biblio.dtos.appuser.AppUserRequestDto;
import cheikh.tall.biblio.entities.AppUser;
import cheikh.tall.biblio.services.interfaces.KeycloakService;

import javax.ws.rs.core.Response;
import java.util.Optional;

public class KeycloakServiceImpl implements KeycloakService {
    @Override
    public Response addUser(AppUserRequestDto appUserRequestDto) {
        return null;
    }

    @Override
    public void updateUser(AppUserRequestDto appUserRequestDto) {

    }

    @Override
    public void addRoleToUser(String username, String rolename) {

    }

    @Override
    public void deleteUser(Optional<AppUser> appUser) {

    }
}
