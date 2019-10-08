package com.lsr.shopit.models.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UploadFileResponse {
	private String fileName;
	private String imageUrl;
	private String contentType;
	private long size;
	
	public UploadFileResponse(String fileName,String imageUrl, String contentType, long size) {
		super();
		this.imageUrl = imageUrl;
		this.fileName = fileName;
		this.contentType = contentType;
		this.size = size;
	}
	
	
}
