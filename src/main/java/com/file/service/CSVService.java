package com.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file.helper.CSVHelper;
import com.file.model.User;
import com.file.repo.UserRepository;

@Service
public class CSVService {
	
	@Autowired
	UserRepository repository;

	public void save(MultipartFile file) {
		try {
			List<User> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
			repository.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public List<User> getAllTutorials() {
		return repository.findAll();
	}
}