package pt.brunocosta.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class PatientExceptionHandlerTest {
    @Test
    void handleNotFoundPatient_shouldReturnNotFoundStatusAndMessage() {
        PatientExceptionHandler handler = new PatientExceptionHandler();
        NotFoundPatientException ex = new NotFoundPatientException();

        ResponseEntity<String> response = handler.handleNotFoundPatient(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
