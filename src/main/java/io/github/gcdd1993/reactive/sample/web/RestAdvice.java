package io.github.gcdd1993.reactive.sample.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import javax.validation.ValidationException;

/**
 * @author Gcdd1993
 */
@Slf4j
@RestControllerAdvice
public class RestAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseMessage<?>> handle(WebExchangeBindException e) {
        log.warn("Returning HTTP 400 Bad Request", e);
        var filedError = e.getBindingResult().getFieldError();
        return Mono.just(
                ResponseMessage.error(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", filedError.getField()).result(filedError.getDefaultMessage())
        );
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseMessage<?>> handleException(ValidationException e) {
        return Mono.just(
                ResponseMessage.error(400, "illegal_argument", e.getMessage()).result(e.getMessage())
        );
    }

//    @ExceptionHandler(WebExchangeBindException.class)
//    @ResponseBody
//    public String handleValidationException(WebExchangeBindException bindException) {
//        return bindException.getBindingResult().toString();
//    }
//
//    @ExceptionHandler(WebExchangeBindException.class)
//    @ResponseBody
//    public String handleBindException(WebExchangeBindException bindException) {
//        return bindException.getBindingResult().toString();
//    }


}
