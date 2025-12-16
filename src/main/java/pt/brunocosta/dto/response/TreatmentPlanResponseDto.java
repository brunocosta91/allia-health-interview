package pt.brunocosta.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TreatmentPlanResponseDto {
    private String id;
    private String patientId;
    private String name;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
}

