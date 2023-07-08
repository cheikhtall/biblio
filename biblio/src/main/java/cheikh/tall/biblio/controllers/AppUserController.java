package cheikh.tall.biblio.controllers;

import cheikh.tall.biblio.dtos.appuser.AppUserRequestDto;
import cheikh.tall.biblio.dtos.appuser.AppUserResponseDto;
import cheikh.tall.biblio.services.interfaces.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AppUserController {
    private AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping(path = "/users")
    public List<AppUserResponseDto> allUsers(){
        return appUserService.allAppUsers();
    }
    @GetMapping(path = "/user/{id}")
    public AppUserResponseDto getAppUserById(@PathVariable Long id){
        return appUserService.getAppUserById(id);
    }
    @PostMapping(path = "/user")
    public ResponseEntity<?> addUser(@RequestBody AppUserRequestDto appUserRequestDto){
        return appUserService.addAppUser(appUserRequestDto);
    }
    @PutMapping(path = "/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody AppUserRequestDto appUserRequestDto){
        return appUserService.updateAppUser(id, appUserRequestDto);
    }
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return appUserService.delete(id);
    }
}
