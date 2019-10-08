package com.lsr.shopit.models.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDetailResp {
	
	private Long id;

	private String name;
	
	private String offerTitle;
	
	private String offerDesc;

	private String state;

	private String city;

	private String location;

	private String ownerName;

	private String userName;

	private String password;

	private String photoUrl;

	private List<String> type;

	private List<String> categories;

	private String openingTime;

	private String closingTime;

	private String ownerEmail;

	private String ownerPhone;

	private Double rating;
}
