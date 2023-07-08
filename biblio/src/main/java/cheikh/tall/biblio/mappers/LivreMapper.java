package cheikh.tall.biblio.mappers;

import cheikh.tall.biblio.dtos.livre.LivreRequestDto;
import cheikh.tall.biblio.dtos.livre.LivreResponseDto;
import cheikh.tall.biblio.entities.Livre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface LivreMapper {
    LivreResponseDto livreToLivreResponseDto(Livre livre);
    Livre livreRequestDtoToLivre(LivreRequestDto livreRequestDto);
}
