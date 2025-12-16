package pt.brunocosta.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pt.brunocosta.domain.model.MedicationRefillOrder;
import pt.brunocosta.dto.response.MedicationRefillOrderResponseDto;

@Mapper(componentModel = "spring")
public interface MedicationRefillOrderMapper {

  @Mapping( target = "id", expression = "java(order.getId() != null ? order.getId().toString() : null)" )
  @Mapping( target = "treatmentPlanId", expression = "java(order.getTreatmentPlan() != null ? order.getTreatmentPlan().getId().toString() : null)" )
  @Mapping( target = "status", expression = "java(order.getStatus().name())" )
  MedicationRefillOrderResponseDto toDto( MedicationRefillOrder order );
}
