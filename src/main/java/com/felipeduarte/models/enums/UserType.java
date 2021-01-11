package com.felipeduarte.models.enums;

public enum UserType {
	
	ADMIN(0, "ROLE_ADMIN"),
	USER(1, "ROLE_USER");
	
	private int code;
	private String description;
	
	private UserType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static UserType toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(UserType x: UserType.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido " + code);
	}
}
