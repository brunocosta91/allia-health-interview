package pt.brunocosta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class TreatmentPlanExceptionHandler {

  @ExceptionHandler(NotFoundTreatmentPlanException.class)
  public ResponseEntity<String> handleNotFoundPatient( NotFoundTreatmentPlanException ex) {
    return ResponseEntity.status( HttpStatus.NOT_FOUND).body( ex.getMessage() );
  }

}
