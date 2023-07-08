package cheikh.tall.biblio.dtos.pret;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PretRequestDto {
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateEmprunt;
}
