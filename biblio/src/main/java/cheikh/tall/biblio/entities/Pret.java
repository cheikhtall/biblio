package cheikh.tall.biblio.entities;

import cheikh.tall.biblio.enums.EtatPret;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pret {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDate dateEmprunt;
    @ManyToOne
    private Adherant adherant;
    @ManyToOne
    @JsonIgnore
    private Livre livre;
    @ManyToOne
    @JsonIgnore
    private Bibliothecaire bibliothecaire;
    private EtatPret etatPret;
}
