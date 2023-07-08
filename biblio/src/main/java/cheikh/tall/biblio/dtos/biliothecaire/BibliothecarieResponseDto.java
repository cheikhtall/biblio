package cheikh.tall.biblio.dtos.biliothecaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BibliothecarieResponseDto {
    private Long id;
    private String email;
    private String firstname;
    private String lastName;
}
