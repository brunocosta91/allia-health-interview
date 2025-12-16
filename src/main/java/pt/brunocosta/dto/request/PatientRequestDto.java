package pt.brunocosta.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record PatientRequestDto(

    UUID id,

    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email
) {}
