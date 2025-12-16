package pt.brunocosta.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.brunocosta.domain.enums.TreatmentPlanStatus;
import pt.brunocosta.domain.model.Patient;
import pt.brunocosta.domain.model.TreatmentPlan;
import pt.brunocosta.exception.NotFoundPatientException;
import pt.brunocosta.repository.PatientRepository;
import pt.brunocosta.repository.TreatmentPlanRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TreatmentPlanServiceImplTest {
    @Mock
    private TreatmentPlanRepository treatmentPlanRepository;
    @Mock
    private PatientRepository patientRepository;
    @InjectMocks
    private TreatmentPlanServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTreatmentPlan_shouldSetPatientAndStatusAndSave() {
        UUID patientId = UUID.randomUUID();
        Patient patient = new Patient();
        TreatmentPlan plan = new TreatmentPlan();
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        when(treatmentPlanRepository.save(plan)).thenReturn(plan);

        TreatmentPlan result = service.createTreatmentPlan(patientId, plan);
        assertNotNull(result);
        assertEquals(patient, plan.getPatient());
        assertEquals(TreatmentPlanStatus.NEW, plan.getStatus());
        verify(patientRepository, times(1)).findById(patientId);
        verify(treatmentPlanRepository, times(1)).save(plan);
    }

    @Test
    void createTreatmentPlan_shouldThrowIfPatientNotFound() {
        UUID patientId = UUID.randomUUID();
        TreatmentPlan plan = new TreatmentPlan();
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());
        assertThrows(NotFoundPatientException.class, () -> service.createTreatmentPlan(patientId, plan));
        verify(patientRepository, times(1)).findById(patientId);
        verify(treatmentPlanRepository, never()).save(any());
    }

    @Test
    void updateStatus_shouldUpdatePlanStatusAndSave() {
        UUID planId = UUID.randomUUID();
        TreatmentPlan plan = new TreatmentPlan();
        plan.setStatus(TreatmentPlanStatus.NEW);
        when(treatmentPlanRepository.findById(planId)).thenReturn(Optional.of(plan));
        when(treatmentPlanRepository.save(plan)).thenReturn(plan);

        TreatmentPlan result = service.updateStatus(planId, "ACTIVE");
        assertNotNull(result);
        assertEquals(TreatmentPlanStatus.ACTIVE, result.getStatus());
        verify(treatmentPlanRepository, times(1)).findById(planId);
        verify(treatmentPlanRepository, times(1)).save(plan);
    }

    @Test
    void updateStatus_shouldThrowIfPlanNotFound() {
        UUID planId = UUID.randomUUID();
        when(treatmentPlanRepository.findById(planId)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> service.updateStatus(planId, "UNKNOWN"));
        verify(treatmentPlanRepository, times(1)).findById(planId);
        verify(treatmentPlanRepository, never()).save(any());
    }

    @Test
    void listByPatient_shouldReturnPlans() {
        UUID patientId = UUID.randomUUID();
        TreatmentPlan plan1 = new TreatmentPlan();
        TreatmentPlan plan2 = new TreatmentPlan();
        List<TreatmentPlan> plans = Arrays.asList(plan1, plan2);
        when(treatmentPlanRepository.findByPatientId(patientId)).thenReturn(plans);

        List<TreatmentPlan> result = service.listByPatient(patientId);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(treatmentPlanRepository, times(1)).findByPatientId(patientId);
    }
}

