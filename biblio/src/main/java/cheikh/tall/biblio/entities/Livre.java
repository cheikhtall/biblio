package cheikh.tall.biblio.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String nomAuteur;
    private String localisation;
    @Temporal(TemporalType.DATE)
    private LocalDate dateParrution;
    private String decription;
    private Integer nombreDisponible;
    private Boolean recent;
    private Boolean plusDemande;
    @OneToMany(mappedBy = "livre", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Pret> prets;
    @JsonIgnore
    @ManyToMany(mappedBy = "livres", fetch = FetchType.LAZY)
    private List<Categorie> categories;
}