package cheikh.tall.biblio.dtos.appuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserRequestDto {
    private String email;
    private String firstname;
    private String lastName;
    private String password;
}
