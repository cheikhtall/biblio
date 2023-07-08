package cheikh.tall.biblio.mappers;

import cheikh.tall.biblio.dtos.appuser.AppUserRequestDto;
import cheikh.tall.biblio.dtos.appuser.AppUserResponseDto;
import cheikh.tall.biblio.entities.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUserResponseDto AppUserTAppUserResponseDto(AppUser appUser);
    AppUser appUserRequestDtoToAppUser(AppUserRequestDto appUserRequestDto);
}
