package com.lsr.shopit.enums;

import java.util.HashMap;
import java.util.Map;

public enum ClothCategory {
	CASUAL_SHIRTS("CasualShirts", ClothType.MEN), 
	FORMALS_SHIRT("FormalShirts", ClothType.MEN), 
	T_SHIRTS("TShirts",ClothType.MEN), 
	SWEATSHIRTS("Sweatshirts",ClothType.MEN), 
	JACKETS("Jackets", ClothType.MEN), 
	BLAZERS_AND_COATS("Blazers", ClothType.MEN),
	KURTAS("Kurtas",ClothType.MEN),
	SHERWANIS("Sherwanis",ClothType.MEN),
	FORMAL_TROUSERS("FormalTrousers",ClothType.MEN),
	CASUAL_TROUSERS("CasualTrousers",ClothType.MEN),
	JEANS("Jeans",ClothType.MEN),
	KURTAS_AND_SUITS("KurtasAndSuits",ClothType.WOMEN),
	ETHNIC_DRESSES("EthnicDresses",ClothType.WOMEN),
	SKIRTS_AND_PALAZZOS("SkirtsandPalazzos",ClothType.WOMEN),
	SAREES_AND_BLOUSES("SareesAndBlouses",ClothType.WOMEN),
	DRESSES_AND_JUMPSUITS("DressesAndJumpsuits",ClothType.WOMEN),
	TOPS("Tops",ClothType.WOMEN);
	
	private String clothCategory;
	private ClothType clothType;
	
	private static Map<String,ClothCategory> mensClothes = new HashMap<>();
	private static Map<String,ClothCategory> womensClothes = new HashMap<>();

	
	static {
		for (ClothCategory category : ClothCategory.values()) {
			if(category.geClothType().equals(ClothType.MEN)) {
				mensClothes.put(category.name().toUpperCase(), category);
			} else if(category.geClothType().equals(ClothType.WOMEN)) {
				womensClothes.put(category.name().toUpperCase(), category);
			}
		}
	}
	
	
	ClothCategory(String clothCategory, ClothType clothType) {
		this.clothCategory = clothCategory;
		this.clothType = clothType;
	}

	public String getClothCategory() {
		return this.clothCategory;
	}
	
	public ClothType geClothType() {
		return this.clothType;
	}
	
	public static ClothCategory get(String clothCategory, ClothType clothType) {
		if (clothType.equals(ClothType.MEN)) {
			return mensClothes.get(clothCategory.toUpperCase());
		} else if (clothType.equals(ClothType.WOMEN)) {
			return womensClothes.get(clothCategory.toUpperCase());
		}

		return null;
	}
	
}
