package com.zproject.managment.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zproject.managment.model.Profile;
import com.zproject.managment.repository.ProfileRepository;
import com.zproject.managment.services.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
    private ProfileRepository profileRepository;
	
	@Override
	public List<Profile> getAll() {
		return profileRepository.findAll();
	}

}
