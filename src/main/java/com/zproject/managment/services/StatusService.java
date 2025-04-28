package com.zproject.managment.services;

import java.util.List;

import com.zproject.managment.model.Status;

public interface StatusService {

	List<Status> getAll();
	boolean createAll();
}
