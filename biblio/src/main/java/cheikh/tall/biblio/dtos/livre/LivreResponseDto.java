package cheikh.tall.biblio.dtos.livre;

import cheikh.tall.biblio.entities.Pret;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivreResponseDto {
    private Long id;
    private String title;
    private String nomAuteur;
    private String localisation;
    private Integer nombreDisponible;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateParrution;
    private String decription;
    private Boolean recent;
    private Boolean plusDemande;
    private List<Pret> prets;
}
