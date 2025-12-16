package pt.brunocosta.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {
    @Test
    void handleValidation_shouldReturnBadRequestAndFieldErrors() {
        // Mock FieldError
        FieldError error1 = new FieldError("object", "field1", "msg1");
        FieldError error2 = new FieldError("object", "field2", "msg2");
        List<FieldError> fieldErrors = Arrays.asList(error1, error2);

        // Mock BindingResult
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        // Mock MethodArgumentNotValidException
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<Map<String, String>> response = handler.handleValidation(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> body = response.getBody();
        assertNotNull(body);
        assertEquals(2, body.size());
        assertEquals("msg1", body.get("field1"));
        assertEquals("msg2", body.get("field2"));
    }

    @Test
    void handleGeneric_shouldReturnInternalServerErrorAndMessage() {
        Exception ex = new Exception("fail");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<String> response = handler.handleGeneric(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error: fail", response.getBody());
    }
}

