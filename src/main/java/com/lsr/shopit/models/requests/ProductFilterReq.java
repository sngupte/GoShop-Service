package com.lsr.shopit.models.requests;

import java.util.List;

import lombok.Data;

@Data
public class ProductFilterReq {
	private Double minPrice;
	private Double maxPrice;
	private List<String> sizesList;
	private List<String> clothCategoryList;
	private List<String> clothTypeList;

}
