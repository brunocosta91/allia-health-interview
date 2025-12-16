package pt.brunocosta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.brunocosta.domain.model.Patient;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

}

