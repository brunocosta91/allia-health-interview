package pt.brunocosta.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MedicationRefillOrderStatusUpdateRequestDto(
    @NotBlank(message = "Status is required")
    String status
) {}
