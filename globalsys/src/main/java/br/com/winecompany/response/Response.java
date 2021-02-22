package br.com.winecompany.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Response<T> {

	@JsonIgnore
	private T data;
	
	private String message;
	
	public Response (T data) {
		this.data = data;
	}

	public Response (String message) {
		this.message = message;
	}
}