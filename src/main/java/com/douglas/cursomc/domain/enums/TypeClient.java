package com.douglas.cursomc.domain.enums;

public enum TypeClient {
	
	PRIVATEINDIVIDUAL(1, "Private Individual"),
	LEGALENTITY(2, "LEGALENTITY");
	
	private int code;
	private String description;
	
	private TypeClient(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static TypeClient toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for (TypeClient typeClient: TypeClient.values()) {
			if(code.equals(typeClient.getCode())) {
				return typeClient;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + code);
	}
}
