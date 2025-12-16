package pt.brunocosta.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicationRefillOrderResponseDto {
    private String id;
    private String treatmentPlanId;
    private LocalDate requestedDate;
    private String status;
}

