package com.lsr.shopit.controller.rest.v1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lsr.shopit.entities.DBFile;
import com.lsr.shopit.models.ResponseWrapper;
import com.lsr.shopit.models.response.UploadFileResponse;
import com.lsr.shopit.service.impl.DBFileStorageService;

@RestController
@RequestMapping("/api/shop")
public class ImageController {

	@Autowired
	private DBFileStorageService fileStorageService;

	@PostMapping("/uploadImage")
	public ResponseWrapper<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		DBFile dbFile = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/shop/getImage/")
				.path(dbFile.getId()).toUriString();

		ResponseWrapper<UploadFileResponse> wrapper = new ResponseWrapper<>();
		UploadFileResponse response = new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
				file.getContentType(), file.getSize());
		wrapper.setData(response);
		wrapper.setMessage("Success");
		wrapper.setSuccess(true);

		return wrapper;
	}

	@PostMapping("/uploadMultipleFiles")
	public List<ResponseWrapper<UploadFileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
	}

	@GetMapping("/getImage/{fileId}")
	public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileId) {
		// Load file from database
		DBFile dbFile = fileStorageService.getFile(fileId);

		InputStream targetStream = new ByteArrayInputStream(dbFile.getData());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(targetStream));
	}
}
