package com.felipeduarte.model.enums;


public enum EntryType {
	
	PROFIT(0,"Profit"),
	EXPENSE(1,"Expense");
	
	private int code;
	private String description;
	
	private EntryType(int code, String description) {
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
	
	public static EntryType toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(EntryType x: EntryType.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido " + code);
	}
}
