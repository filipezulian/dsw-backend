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
	
	@Override
	public boolean createAll() {
	    List<Profile> existingProfiles = profileRepository.findAll();

	    if (existingProfiles.isEmpty()) {
	    	Profile admin = new Profile();
	        admin.setName("admin");

	        Profile manager = new Profile();
	        manager.setName("manager");

	        Profile member = new Profile();
	        member.setName("member");

	        profileRepository.saveAll(List.of(admin, manager, member));
	        return true;
	    }
	    return false;
	}
}