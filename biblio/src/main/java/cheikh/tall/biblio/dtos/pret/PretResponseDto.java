package cheikh.tall.biblio.dtos.pret;

import cheikh.tall.biblio.entities.Adherant;
import cheikh.tall.biblio.entities.Bibliothecaire;
import cheikh.tall.biblio.entities.Livre;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PretResponseDto {
    private Long id;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateEmprunt;
    private Adherant adherant;
    private Livre livre;
    private Bibliothecaire bibliothecaire;
}
