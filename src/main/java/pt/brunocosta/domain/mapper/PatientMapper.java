package pt.brunocosta.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pt.brunocosta.domain.model.Patient;
import pt.brunocosta.dto.request.PatientRequestDto;
import pt.brunocosta.dto.response.PatientResponseDto;

import java.util.UUID;

@Mapper( componentModel = "spring" )
public interface PatientMapper {

  /**
   * Converts a PatientRequestDto to a Patient domain object.
   */
  @Mapping( target = "id", ignore = true )
  Patient toDomain( PatientRequestDto dto );

  /**
   * Converts a Patient domain object to a PatientResponseDto.
   */
  @Mapping(target = "id", expression = "java(patient.getId() != null ? patient.getId().toString() : null)")
  PatientResponseDto toDto(Patient patient);
}
