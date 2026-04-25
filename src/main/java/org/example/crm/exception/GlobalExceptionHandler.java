package org.example.crm.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Обработка нашего кастомного бизнес-исключения
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDto> handleBusinessException(BusinessException ex) {
        ErrorDto error = new ErrorDto(
                ex.getMessage(),
                LocalDateTime.now()
        );
        // Возвращаем статус 400 (Bad Request), так как это ошибка в данных пользователя
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Бонус: перехват всех остальных непредвиденных ошибок (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGlobalException(Exception ex) {
        ErrorDto error = new ErrorDto(
                "Произошла внутренняя ошибка сервера. Обратитесь к администратору.",
                LocalDateTime.now()
        );
        // Логируем реальную ошибку в консоль, чтобы мы могли её починить
        ex.printStackTrace();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleGlobalException(MethodArgumentNotValidException ex) {
        ErrorDto error = new ErrorDto(
                ex.getMessage(),
                LocalDateTime.now()
        );
        // Логируем реальную ошибку в консоль, чтобы мы могли её починить
        ex.printStackTrace();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @Getter
    @Setter
    @AllArgsConstructor
    @Schema(description = "Унифицированная структура ошибки")
    class ErrorDto {
        @Schema(description = "Сообщение об ошибке для пользователя")
        private String message;

        @Schema(description = "Временная метка ошибки")
        private LocalDateTime timestamp;
    }

