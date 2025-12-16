package pt.brunocosta.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.brunocosta.domain.model.TreatmentPlan;

import java.util.List;
import java.util.UUID;

public interface TreatmentPlanService {
    /**
     * Creates a new treatment plan for a given patient.
     *
     * @param patientId the UUID of the patient to whom the treatment plan belongs
     * @param plan the TreatmentPlan object to be created (without id and patient set)
     * @return the created TreatmentPlan with generated id and patient set
     */
    TreatmentPlan createTreatmentPlan(UUID patientId, TreatmentPlan plan);

    /**
     * Updates the status of an existing treatment plan.
     *
     * @param planId the UUID of the treatment plan to update
     * @param status the new status to set (must match TreatmentPlanStatus enum)
     * @return the updated TreatmentPlan
     */
    TreatmentPlan updateStatus(UUID planId, String status);

    /**
     * Lists all treatment plans for a given patient.
     *
     * @param patientId the UUID of the patient
     * @return a list of TreatmentPlan objects belonging to the patient
     */
    List<TreatmentPlan> listByPatient(UUID patientId);
}
