package com.lsr.shopit.enums;

import java.util.HashMap;
import java.util.Map;

public enum ClothType {
	MEN("Men"), WOMEN("Women");

	private String clothType;

	ClothType(String clothType) {
		this.clothType = clothType;
	}

	public String getClothType() {
		return this.clothType;
	}

	private static final Map<String, ClothType> lookup = new HashMap<>();

	static {
		for (ClothType type : ClothType.values()) {
			lookup.put(type.getClothType().toUpperCase(), type);
		}
	}

	public static ClothType get(String type) {
		return lookup.get(type.toUpperCase());
	}
}
