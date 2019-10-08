package com.lsr.shopit.models.requests;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterShopReq {

	private String name;

	private String offerTitle;

	private String offerDesc;

	private String state;

	private String city;

	private String location;

	private String address;

	private String photoUrl;

	private List<String> type;

	private List<String> categories;

	private String openingTime;

	private String closingTime;

	private String phone;

	private Boolean parkingAvail;

	private Boolean changingRoomAvail;

}
