package cheikh.tall.biblio.dtos.livre;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivreRequestDto {
    private String title;
    private String nomAuteur;
    private String localisation;
    private Integer nombreDisponible;
    private Boolean plusDemande;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateParrution;
    private String decription;
}
