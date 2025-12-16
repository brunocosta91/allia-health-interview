package pt.brunocosta.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pt.brunocosta.domain.model.TreatmentPlan;
import pt.brunocosta.dto.request.TreatmentPlanRequestDto;
import pt.brunocosta.dto.response.TreatmentPlanResponseDto;

@Mapper(componentModel = "spring")
public interface TreatmentPlanMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping( target = "status", ignore = true )
  @Mapping(target = "patient", ignore = true)
  TreatmentPlan toDomain(TreatmentPlanRequestDto dto);

  @Mapping(target = "id", expression = "java(plan.getId() != null ? plan.getId().toString() : null)")
  @Mapping(target = "patientId", expression = "java(plan.getPatient() != null ? plan.getPatient().getId().toString() : null)")
  TreatmentPlanResponseDto toDto(TreatmentPlan plan);
}
