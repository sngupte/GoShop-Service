package com.lsr.shopit.models.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
	private String type;
	private List<String> itemList;
}
