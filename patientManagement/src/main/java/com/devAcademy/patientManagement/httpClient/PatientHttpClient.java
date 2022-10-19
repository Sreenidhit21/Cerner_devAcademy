/**
 * 
 */
package com.devAcademy.patientManagement.httpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.devAcademy.patientManagement.entity.PatientEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ST105714
 *
 *         HttpClient for PatientManagement APIs
 */
public class PatientHttpClient {
	private static final String PATIENT_API_URL = "http://localhost:8080/patient/";
	private static final String GET_BY_GOVT_ID_API_URL = "http://localhost:8080/patient/govtId/";
	private static final String GET_BY_NAME_API_URL = "http://localhost:8080/patient/patientName/";

	/**
	 * 
	 */
	public PatientHttpClient() {
	}

	/**
	 * @param id
	 * @return PatientEntity
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> getPatientDetailsById(Long id) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().GET().header("Content.Type", "application/json")
				.uri(URI.create(PATIENT_API_URL + id)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		return response;
	}

	/**
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> getPatientDetailsByGovtId(String id) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().GET().header("Content.Type", "application/json")
				.uri(URI.create(GET_BY_GOVT_ID_API_URL + id)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		return response;
	}

	/**
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> getPatientDetailsByName(String name) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().GET().header("Content.Type", "application/json")
				.uri(URI.create(GET_BY_NAME_API_URL + name)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	return response;
	}

	/**
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> deletePatientDetails(Long id) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().DELETE().header("Content.Type", "application/json")
				.uri(URI.create(PATIENT_API_URL + id)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return response;
	}

	/**
	 * @param patientEntity
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static PatientEntity updatePatientDetails(PatientEntity patientEntity)
			throws IOException, InterruptedException {
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(patientEntity);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(requestBody))
				.header("Content.Type", "application/json").uri(URI.create(PATIENT_API_URL)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		PatientEntity responsetEntity = mapper.readValue(response.body(), new TypeReference<PatientEntity>() {
		});

		return responsetEntity;
	}

	/**
	 * @param patientEntity
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static PatientEntity createPatientDetails(PatientEntity patientEntity)
			throws IOException, InterruptedException {
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(patientEntity);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.header("Content.Type", "application/json").uri(URI.create(PATIENT_API_URL)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		PatientEntity responsetEntity = mapper.readValue(response.body(), new TypeReference<PatientEntity>() {
		});

		return responsetEntity;
	}

}
