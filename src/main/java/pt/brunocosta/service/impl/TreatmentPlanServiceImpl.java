package pt.brunocosta.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.brunocosta.domain.model.Patient;
import pt.brunocosta.domain.model.TreatmentPlan;
import pt.brunocosta.domain.enums.TreatmentPlanStatus;
import pt.brunocosta.exception.NotFoundPatientException;
import pt.brunocosta.exception.NotFoundTreatmentPlanException;
import pt.brunocosta.repository.PatientRepository;
import pt.brunocosta.repository.TreatmentPlanRepository;
import pt.brunocosta.service.TreatmentPlanService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TreatmentPlanServiceImpl implements TreatmentPlanService {

    private final TreatmentPlanRepository treatmentPlanRepository;
    private final PatientRepository patientRepository;

    @Override
    public TreatmentPlan createTreatmentPlan(UUID patientId, TreatmentPlan plan) {
        Patient patient = patientRepository.findById(patientId).orElseThrow( NotFoundPatientException::new );
        plan.setPatient(patient);
        plan.setStatus(TreatmentPlanStatus.NEW );
        return treatmentPlanRepository.save(plan);
    }

    @Override
    public TreatmentPlan updateStatus(UUID planId, String status) {
        TreatmentPlan plan = treatmentPlanRepository.findById(planId).orElseThrow( NotFoundTreatmentPlanException::new );
        plan.setStatus(TreatmentPlanStatus.valueOf(status));
        return treatmentPlanRepository.save(plan);
    }

    @Override
    public List<TreatmentPlan> listByPatient(UUID patientId) {
        return treatmentPlanRepository.findByPatientId(patientId);
    }
}

