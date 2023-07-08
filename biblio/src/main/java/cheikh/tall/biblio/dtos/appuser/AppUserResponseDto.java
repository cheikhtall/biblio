package cheikh.tall.biblio.dtos.appuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserResponseDto {
    private Long id;
    private String email;
    private String firstname;
    private String lastName;
}
