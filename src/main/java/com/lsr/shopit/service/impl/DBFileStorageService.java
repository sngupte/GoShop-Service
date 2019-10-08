package com.lsr.shopit.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lsr.shopit.entities.DBFile;
import com.lsr.shopit.exceptions.BadRequestException;
import com.lsr.shopit.exceptions.DataNotFoundException;
import com.lsr.shopit.repositories.DBFileRepository;

@Service
public class DBFileStorageService {
	@Autowired
	private DBFileRepository dbFileRepository;

	public DBFile storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new BadRequestException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

			return dbFileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new BadRequestException("Could not store file " + fileName + ". Please try again!");
		}
	}

	public DBFile getFile(String fileId) {
		return dbFileRepository.findById(fileId)
				.orElseThrow(() -> new DataNotFoundException("File not found with id " + fileId));
	}
}
