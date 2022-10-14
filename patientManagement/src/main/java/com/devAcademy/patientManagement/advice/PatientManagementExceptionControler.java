package com.devAcademy.patientManagement.advice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devAcademy.patientManagement.exception.GovtIdOrReasonForNotSharingRequiredException;
import com.devAcademy.patientManagement.exception.PatientNotFoundException;

/**
 * PatientManagementExceptionControler
 */
@RestControllerAdvice
public class PatientManagementExceptionControler {

	/**
	 * @param MethodArgumentNotValidException
	 * @return Map<String, String> exceptionMap
	 *         <p>
	 *         This method handle invalid argument in entity
	 *         </p>
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgumentException(MethodArgumentNotValidException ex) {
		Map<String, String> exceptionMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			exceptionMap.put(error.getField(), error.getDefaultMessage());
		});
		return exceptionMap;
	}

	/**
	 * @param GovtIdOrReasonForNotSharingRequiredException
	 * @return Map<String, String> exceptionMap
	 *         <p>
	 *         This method handle GovtId Or ReasonForNotSharing Required Exception
	 *         </p>
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(GovtIdOrReasonForNotSharingRequiredException.class)
	public Map<String, String> handleGovtIdOrReasonForNotSharingRequiredException(
			GovtIdOrReasonForNotSharingRequiredException ex) {
		Map<String, String> exceptionMap = new HashMap<>();
		exceptionMap.put("error message", ex.getMessage());
		return exceptionMap;
	}

	/**
	 * @param SQLException
	 * @return Map<String, String> exceptionMap
	 *         <p>
	 *         This method handleSQLException
	 *         </p>
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SQLException.class)
	public Map<String, String> handleSQLException(SQLException ex) {
		Map<String, String> exceptionMap = new HashMap<>();
		exceptionMap.put("error message", ex.getMessage());
		return exceptionMap;
	}

	/**
	 * @param EmptyResultDataAccessException
	 * @return Map<String, String> exceptionMap
	 *         <p>
	 *         This method EmptyResultDataAccessException
	 *         </p>
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public Map<String, String> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
		Map<String, String> exceptionMap = new HashMap<>();
		exceptionMap.put("error message", ex.getMessage());
		return exceptionMap;
	}

	/**
	 * @param PatientNotFoundException
	 * @return Map<String, String> exceptionMap
	 *         <p>
	 *         This method handle PatientNotFoundException
	 *         </p>
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(PatientNotFoundException.class)
	public Map<String, String> handlePatientNotFoundException(PatientNotFoundException ex) {
		Map<String, String> exceptionMap = new HashMap<>();
		exceptionMap.put("error message", ex.getMessage());
		return exceptionMap;
	}

	public PatientManagementExceptionControler() {
	}

}
