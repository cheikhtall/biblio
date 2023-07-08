package cheikh.tall.biblio.dtos.biliothecaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BibliothecaireRequestDto {
    private String email;
    private String firstname;
    private String lastName;
    private String password;
}
