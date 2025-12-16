package pt.brunocosta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.brunocosta.domain.model.TreatmentPlan;

import java.util.List;
import java.util.UUID;

public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, UUID> {
    List<TreatmentPlan> findByPatientId(UUID patientId);
}

