package com.lsr.shopit.models.requests;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchShopsReq {
	private List<String> categoryList;
	private List<String> typeList;
	private String location;
}
