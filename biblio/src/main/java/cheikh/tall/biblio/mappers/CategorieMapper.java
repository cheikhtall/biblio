package cheikh.tall.biblio.mappers;

import cheikh.tall.biblio.dtos.categorie.CategorieRequestDto;
import cheikh.tall.biblio.dtos.categorie.CategorieResponseDto;
import cheikh.tall.biblio.entities.Categorie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorieMapper {
    CategorieResponseDto categorieToCategorieResponseDto(Categorie categorie);
    Categorie categorieRequestDtoToCategorie(CategorieRequestDto categorieRequestDto);
}
