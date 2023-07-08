package cheikh.tall.biblio.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@DiscriminatorValue("BIBL")
public class Bibliothecaire extends AppUser{
    public Bibliothecaire(Long id, String email, String firstname, String lastName, String password) {
        super(id, email, firstname, lastName, password);
    }
    @OneToMany(mappedBy = "bibliothecaire", fetch = FetchType.LAZY)
    private List<Pret> prets;
}
