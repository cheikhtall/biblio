package cheikh.tall.biblio.mappers;

import cheikh.tall.biblio.dtos.adherant.AdherantRequestDto;
import cheikh.tall.biblio.dtos.adherant.AdherantResponseDto;
import cheikh.tall.biblio.entities.Adherant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdherantMapper {
    AdherantResponseDto adherantToAdherantResponseDto(Adherant adherant);
    Adherant adherantRequestDtoToAdherant(AdherantRequestDto adherantRequestDto);
}
