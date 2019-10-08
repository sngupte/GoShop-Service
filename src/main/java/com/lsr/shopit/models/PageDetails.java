package com.lsr.shopit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
public class PageDetails {

	@JsonIgnore
	public static final int DEFAULT_PAGE_SIZE = 50;

	// Page number requested for
	private int page = 1;

	// Max results per page
	private int size = DEFAULT_PAGE_SIZE;

	// Total pages based on the Data available
	private int totalPages = 1;

	// Total data count.
	private long totalCount = 0L;

	public PageDetails(int page, int size, int totalPages, long totalCount) {
		super();
		this.page = page;
		this.size = size;
		this.totalPages = totalPages;
		this.totalCount = totalCount;
	}


}
