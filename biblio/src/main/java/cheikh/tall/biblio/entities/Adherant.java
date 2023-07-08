package cheikh.tall.biblio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@DiscriminatorValue("ADHE")
public class Adherant extends AppUser{
    public Adherant(Long id, String email, String firstname, String lastName, String password) {
        super(id, email, firstname, lastName, password);
    }

    private String phone;
    private String address;
    @Temporal(TemporalType.DATE)
    private LocalDate birth;
    @Transient
    private Integer age;
    @OneToMany(mappedBy = "adherant", fetch = FetchType.LAZY)
    private List<Pret> prets;

    public Integer getAge(){
        return Period.between(birth, LocalDate.now()).getYears();
    }
}
