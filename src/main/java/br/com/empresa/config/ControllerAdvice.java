package br.com.empresa.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, Object> resposta = new HashMap<>();

		List<ErrorObject> errors = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(e -> {
			errors.add(new ErrorObject(messageSource.getMessage(e, LocaleContextHolder.getLocale()), e.getField(),
					e.getRejectedValue()));
		});

		resposta.put("code", HttpStatus.BAD_REQUEST);
		resposta.put("status", "error");
		resposta.put("erros", errors);
		return resposta;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IllegalStateException.class)
	public Map<String, Object> handleResponseStatusException(IllegalStateException ex) {
		Map<String, Object> resposta = new HashMap<>();
		resposta.put("mensagem", "Funcinário não pode entrar e sair do mesmo departamento");
		resposta.put("status", "error");
		resposta.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
		return resposta;
	}

}