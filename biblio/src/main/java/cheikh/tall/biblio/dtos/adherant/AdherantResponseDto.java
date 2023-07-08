package cheikh.tall.biblio.dtos.adherant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdherantResponseDto {
    private Long id;
    private String email;
    private String firstname;
    private String lastName;
    private String phone;
    private String address;
    @JsonFormat(pattern="yyyy/MM/dd")
    private LocalDate birth;
}
