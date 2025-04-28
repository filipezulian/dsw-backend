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

	@Override
	public boolean createAll() {
		List<Status> existingStatuses = statusRepository.findAll();
	
	    if (existingStatuses.isEmpty()) {
	        Status active = new Status();
	        active.setName("Active");
	
	        Status onHold = new Status();
	        onHold.setName("On Hold");
	
	        Status completed = new Status();
	        completed.setName("Completed");
	
	        statusRepository.saveAll(List.of(active, onHold, completed));
	        return true;
	    }
	    return false;
	}

}