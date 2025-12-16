package pt.brunocosta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.brunocosta.domain.mapper.MedicationRefillOrderMapper;
import pt.brunocosta.domain.mapper.TreatmentPlanMapper;
import pt.brunocosta.domain.model.MedicationRefillOrder;
import pt.brunocosta.domain.model.TreatmentPlan;
import pt.brunocosta.dto.request.TreatmentPlanStatusUpdateRequestDto;
import pt.brunocosta.dto.response.MedicationRefillOrderResponseDto;
import pt.brunocosta.dto.response.TreatmentPlanResponseDto;
import pt.brunocosta.service.MedicationRefillOrderService;
import pt.brunocosta.service.TreatmentPlanService;

import java.util.UUID;

@RestController
@RequestMapping( "/treatment-plans" )
@RequiredArgsConstructor
public class TreatmentPlanController {
  private final TreatmentPlanService treatmentPlanService;
  private final TreatmentPlanMapper treatmentPlanMapper;
  private final MedicationRefillOrderService orderService;
  private final MedicationRefillOrderMapper orderMapper;

  @PatchMapping( "/{id}/status" )
  public ResponseEntity<TreatmentPlanResponseDto> updateStatus(
      @PathVariable UUID id, @Validated @RequestBody TreatmentPlanStatusUpdateRequestDto dto
  ) {
    TreatmentPlan updated = treatmentPlanService.updateStatus( id, dto.status() );
    return ResponseEntity.ok( treatmentPlanMapper.toDto( updated ) );
  }

  @PostMapping( "/{id}/refill-orders" )
  public ResponseEntity<MedicationRefillOrderResponseDto> create( @PathVariable UUID id ) {
    MedicationRefillOrder saved = orderService.create( id );
    return new ResponseEntity<>( orderMapper.toDto( saved ), HttpStatus.CREATED );
  }
}

