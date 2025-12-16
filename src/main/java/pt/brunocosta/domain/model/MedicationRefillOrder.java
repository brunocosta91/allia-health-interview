package pt.brunocosta.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import pt.brunocosta.domain.enums.MedicationRefillOrderStatus;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "medication_refill_orders")
public class MedicationRefillOrder {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "treatment_plan_id")
    private TreatmentPlan treatmentPlan;

    @Column(name = "requested_date", nullable = false)
    private LocalDate requestedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedicationRefillOrderStatus status;
}

