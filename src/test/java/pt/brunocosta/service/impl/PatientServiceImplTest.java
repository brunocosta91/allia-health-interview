package pt.brunocosta.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pt.brunocosta.domain.model.Patient;
import pt.brunocosta.exception.NotFoundPatientException;
import pt.brunocosta.repository.PatientRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PatientServiceImplTest {
    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPatient_shouldSaveAndReturnPatient() {
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setEmail("john@example.com");
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient result = patientService.createPatient(patient);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void getPatientById_shouldReturnPatientIfExists() {
        UUID id = UUID.randomUUID();
        Patient patient = new Patient();
        patient.setId(id);
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        Patient result = patientService.getPatientById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(patientRepository, times(1)).findById(id);
    }

    @Test
    void getPatientById_shouldThrowExceptionIfNotFound() {
        UUID id = UUID.randomUUID();
        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundPatientException.class, () -> patientService.getPatientById(id));
        verify(patientRepository, times(1)).findById(id);
    }

    @Test
    void listPatients_shouldReturnPageOfPatients() {
        Patient patient1 = new Patient();
        patient1.setName("Alice");
        patient1.setEmail("alice@example.com");
        Patient patient2 = new Patient();
        patient2.setName("Bob");
        patient2.setEmail("bob@example.com");
        Page<Patient> page = new PageImpl<>(Arrays.asList(patient1, patient2));
        PageRequest pageable = PageRequest.of(0, 10);
        when(patientRepository.findAll(pageable)).thenReturn(page);

        Page<Patient> result = patientService.listPatients(pageable);
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        verify(patientRepository, times(1)).findAll(pageable);
    }
}

