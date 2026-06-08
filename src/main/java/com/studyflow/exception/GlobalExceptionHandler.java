package com.studyflow.exception;

import com.studyflow.dto.ErroRespostaDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroRespostaDTO> tratarEntidadeNaoEncontrada(
            EntidadeNaoEncontradaException exception,
            HttpServletRequest request) {
        return criarResposta(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroRespostaDTO> tratarRegraNegocio(
            RegraNegocioException exception,
            HttpServletRequest request) {
        return criarResposta(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDTO> tratarValidacao(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {
        String mensagem = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return criarResposta(HttpStatus.BAD_REQUEST, mensagem, request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroRespostaDTO> tratarErroInterno(Exception exception, HttpServletRequest request) {
        return criarResposta(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno da aplicacao.", request.getRequestURI());
    }

    private ResponseEntity<ErroRespostaDTO> criarResposta(HttpStatus status, String mensagem, String caminho) {
        ErroRespostaDTO erro = new ErroRespostaDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                mensagem,
                caminho);

        return ResponseEntity.status(status).body(erro);
    }
}
