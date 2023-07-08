package cheikh.tall.biblio.mappers;

import cheikh.tall.biblio.dtos.pret.PretRequestDto;
import cheikh.tall.biblio.dtos.pret.PretResponseDto;
import cheikh.tall.biblio.entities.Pret;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PretMapper {
    PretResponseDto pretToPretResonseDto(Pret pret);
    Pret pretRsquestDtoToPret(PretRequestDto pretRequestDto);
}
