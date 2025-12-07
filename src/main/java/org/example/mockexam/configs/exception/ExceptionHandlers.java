package org.example.mockexam.configs.exception;

import org.example.mockexam.configs.util.LoggerUtil;
import org.example.mockexam.modules.dto.BaseMessage;
import org.slf4j.Logger;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {
    private final ResourceBundleMessageSource errorMessageSource;
    private final Logger log = LoggerUtil.log(ExceptionHandlers.class);

    public ExceptionHandlers(ResourceBundleMessageSource errorMessageSource) {
        this.errorMessageSource = errorMessageSource;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleGeneralException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest().body("Osha Gap");
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<?> handleAuthorizationDeniedException(AuthorizationDeniedException exception) {
        log.error(exception.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        log.error(errors.toString());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error(exception.getMostSpecificCause().toString());
        return ResponseEntity.badRequest().body(new BaseMessage(400, exception.getMessage()));
    }

    @ExceptionHandler(MockExamException.class)
    public ResponseEntity<BaseMessage> handleMockExamException(MockExamException exception) {
        log.error(exception.getErrorMessage(errorMessageSource).getMessage());
        return ResponseEntity.badRequest().body(exception.getErrorMessage(errorMessageSource));
    }
}
