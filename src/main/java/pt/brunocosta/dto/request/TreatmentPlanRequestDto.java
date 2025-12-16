package pt.brunocosta.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record TreatmentPlanRequestDto(
    @NotBlank(message = "Name is required")
    String name,

    @NotNull(message = "Start date is required")
    LocalDate startDate,
    LocalDate endDate
) {}
