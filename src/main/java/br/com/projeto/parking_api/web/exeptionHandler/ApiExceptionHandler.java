package br.com.projeto.parking_api.web.exeptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.projeto.parking_api.exception.UsernameValueUniqueException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class) 
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        log.error("Error - ", ex);
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, status, "Campo(s) inválido(s)", result));
    }

    @ExceptionHandler(UsernameValueUniqueException.class)
    public ResponseEntity<ErrorMessage> usernameValueUniqueException(UsernameValueUniqueException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        log.error("Error - ", ex);
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, status, ex.getMessage()));
    }
}
