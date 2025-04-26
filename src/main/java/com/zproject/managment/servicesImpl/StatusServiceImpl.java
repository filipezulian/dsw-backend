package com.zproject.managment.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zproject.managment.model.Status;
import com.zproject.managment.repository.StatusRepository;
import com.zproject.managment.services.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
    private StatusRepository statusRepository;
	
	@Override
	public List<Status> getAll() {
		return statusRepository.findAll();
	}

}