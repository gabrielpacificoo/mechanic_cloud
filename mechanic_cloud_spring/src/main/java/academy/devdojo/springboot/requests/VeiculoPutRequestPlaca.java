package academy.devdojo.springboot.requests;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class VeiculoPutRequestPlaca {
    private Long id;
    private String placa;
}
