package cheikh.tall.biblio.mappers;

import cheikh.tall.biblio.dtos.biliothecaire.BibliothecaireRequestDto;
import cheikh.tall.biblio.dtos.biliothecaire.BibliothecarieResponseDto;
import cheikh.tall.biblio.entities.Bibliothecaire;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BiblilothecaireMapper {
    BibliothecarieResponseDto bliblioToBlioResponseDto(Bibliothecaire bibliothecaire);
    Bibliothecaire biblioRquestToBibliothecaire(BibliothecaireRequestDto bibliothecaireRequestDto);
}
