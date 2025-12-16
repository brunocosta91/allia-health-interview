package pt.brunocosta.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import pt.brunocosta.domain.enums.TreatmentPlanStatus;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "treatment_plans")
public class TreatmentPlan {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TreatmentPlanStatus status;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
}

