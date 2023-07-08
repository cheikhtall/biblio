package cheikh.tall.biblio.dtos.categorie;

import cheikh.tall.biblio.entities.Livre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorieResponseDto {
    private Long id;
    private String name;
    private String description;
    private List<Livre> livres;
}
