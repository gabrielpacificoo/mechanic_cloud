package academy.devdojo.springboot.requests;

import lombok.Data;

@Data
public class AnimalPutRequestBody {
    private Long id;
    private String type;
}
