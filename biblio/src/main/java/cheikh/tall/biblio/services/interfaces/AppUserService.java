package cheikh.tall.biblio.services.interfaces;

import cheikh.tall.biblio.dtos.appuser.AppUserRequestDto;
import cheikh.tall.biblio.dtos.appuser.AppUserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppUserService {
    ResponseEntity<?> addAppUser(AppUserRequestDto appUserRequestDto);
    ResponseEntity<?> updateAppUser(Long id, AppUserRequestDto appUserRequestDto);
    ResponseEntity<?> delete(Long id);
    AppUserResponseDto getAppUserById(Long id);
    List<AppUserResponseDto> allAppUsers();
}
