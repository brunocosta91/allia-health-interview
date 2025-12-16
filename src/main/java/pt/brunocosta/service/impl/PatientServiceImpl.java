package pt.brunocosta.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.brunocosta.domain.model.Patient;
import pt.brunocosta.exception.NotFoundPatientException;
import pt.brunocosta.repository.PatientRepository;
import pt.brunocosta.service.PatientService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(UUID id) {
        return patientRepository.findById(id).orElseThrow( NotFoundPatientException::new );
    }

    @Override
    public Page<Patient> listPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }
}
