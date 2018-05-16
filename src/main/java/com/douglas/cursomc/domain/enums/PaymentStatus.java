package com.douglas.cursomc.domain.enums;

public enum PaymentStatus {
	
	PENDING (1, "Pending"),
	PAID (2, "Paid"),
	CANCELLED (3, "Cancelled");

	private int code;
	private String description;

	private PaymentStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static PaymentStatus toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for (PaymentStatus status: PaymentStatus.values()) {
			if(code.equals(status.getCode())) {
				return status;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + code);
	}
}
