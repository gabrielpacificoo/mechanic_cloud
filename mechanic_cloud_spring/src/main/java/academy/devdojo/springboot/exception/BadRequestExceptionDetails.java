package academy.devdojo.springboot.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BadRequestExceptionDetails {
    private String title;
    private int status;
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;
}
