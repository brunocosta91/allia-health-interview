package pt.brunocosta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pt.brunocosta.dto.request.PatientRequestDto;
import pt.brunocosta.dto.response.PatientResponseDto;
import pt.brunocosta.domain.mapper.PatientMapper;
import pt.brunocosta.domain.model.Patient;
import pt.brunocosta.service.PatientService;
import pt.brunocosta.domain.mapper.TreatmentPlanMapper;
import pt.brunocosta.domain.model.TreatmentPlan;
import pt.brunocosta.dto.request.TreatmentPlanRequestDto;
import pt.brunocosta.dto.response.TreatmentPlanResponseDto;
import pt.brunocosta.service.TreatmentPlanService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final TreatmentPlanService treatmentPlanService;
    private final TreatmentPlanMapper treatmentPlanMapper;

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Validated @RequestBody PatientRequestDto requestDto) {
        Patient patient = patientMapper.toDomain(requestDto);
        Patient saved = patientService.createPatient(patient);
        PatientResponseDto responseDto = patientMapper.toDto(saved);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponseDto>> listPatients(Pageable pageable) {
        Page<Patient> patients = patientService.listPatients(pageable);
        Page<PatientResponseDto> responseDtos = patients.map(patientMapper::toDto);
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable UUID id) {
        Patient patient = patientService.getPatientById(id);
        PatientResponseDto responseDto = patientMapper.toDto(patient);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/{id}/treatment-plans")
    public ResponseEntity<TreatmentPlanResponseDto> createTreatmentPlan(@PathVariable UUID id, @Validated @RequestBody TreatmentPlanRequestDto dto) {
        TreatmentPlan plan = treatmentPlanMapper.toDomain(dto);
        TreatmentPlan saved = treatmentPlanService.createTreatmentPlan(id, plan);
        return new ResponseEntity<>(treatmentPlanMapper.toDto(saved), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/treatment-plans")
    public ResponseEntity<List<TreatmentPlanResponseDto>> listTreatmentPlans( @PathVariable UUID id) {
        List<TreatmentPlan> plans = treatmentPlanService.listByPatient(id);
        List<TreatmentPlanResponseDto> dtos =
            plans.stream().map(treatmentPlanMapper::toDto).collect( Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
