package pt.brunocosta.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.brunocosta.domain.model.Patient;

import java.util.UUID;

public interface PatientService {
    /**
     * Creates a new patient in the system.
     *
     * @param patient the Patient object to be persisted
     * @return the saved Patient with generated ID
     */
    Patient createPatient(Patient patient);

    /**
     * Retrieves a patient by its unique identifier.
     *
     * @param id the UUID of the patient
     * @return the Patient corresponding to the given id
     * @throws pt.brunocosta.exception.NotFoundPatientException if not found
     */
    Patient getPatientById(UUID id);

    /**
     * Lists patients in a paginated format.
     *
     * @param pageable pagination information
     * @return a page of patients
     */
    Page<Patient> listPatients(Pageable pageable);
}
