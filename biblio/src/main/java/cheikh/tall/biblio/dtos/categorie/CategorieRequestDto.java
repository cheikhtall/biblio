package cheikh.tall.biblio.dtos.categorie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorieRequestDto {
    private String name;
    private String description;
}