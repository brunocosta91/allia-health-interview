package pt.brunocosta.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TreatmentPlanStatusUpdateRequestDto(
    @NotBlank(message = "Status is required")
    String status
) {}
