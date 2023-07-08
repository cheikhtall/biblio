package cheikh.tall.biblio.services.impl;

import cheikh.tall.biblio.dtos.appuser.AppUserRequestDto;
import cheikh.tall.biblio.dtos.appuser.AppUserResponseDto;
import cheikh.tall.biblio.entities.AppUser;
import cheikh.tall.biblio.exceptions.CustomException;
import cheikh.tall.biblio.mappers.AppUserMapper;
import cheikh.tall.biblio.repositoties.AppUserRepository;
import cheikh.tall.biblio.services.interfaces.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private AppUserMapper appUserMapper;

    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    @Override
    public ResponseEntity<?> addAppUser(AppUserRequestDto appUserRequestDto) {
        AppUser appUser = appUserMapper.appUserRequestDtoToAppUser(appUserRequestDto);
        appUserRepository.save(appUser);
        return ResponseEntity.ok("Un nouvel utilisateur vient d'être créé!");
    }

    @Override
    public ResponseEntity<?> updateAppUser(Long id, AppUserRequestDto appUserRequestDto) {
        AppUser a = appUserRepository.findById(id).orElseThrow(
            ()-> new CustomException("Utilisateur introuvable!"));
        AppUser appUser = appUserMapper.appUserRequestDtoToAppUser(appUserRequestDto);
        appUser.setId(a.getId());
        appUserRepository.save(appUser);
        return ResponseEntity.ok("Modification enregistrée!");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(
                ()-> new CustomException("Utilisateur introuvable!"));
        appUserRepository.delete(appUser);
        return ResponseEntity.ok("Suppression enregistrée!");
    }

    @Override
    public AppUserResponseDto getAppUserById(Long id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(
                ()-> new CustomException("Utilisateur introuvable!"));
        return appUserMapper.AppUserTAppUserResponseDto(appUser);
    }

    @Override
    public List<AppUserResponseDto> allAppUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
                List<AppUserResponseDto> appUserResponseDtos = appUsers.stream().map(
                appUser -> appUserMapper.AppUserTAppUserResponseDto(appUser)
        ).collect(Collectors.toList());
        return appUserResponseDtos;
    }
}
