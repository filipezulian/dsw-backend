package com.zproject.managment.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zproject.managment.exception.ResourceNotFoundException;
import com.zproject.managment.model.Project;
import com.zproject.managment.model.User;
import com.zproject.managment.model.UserProject;
import com.zproject.managment.repository.ProfileRepository;
import com.zproject.managment.repository.ProjectRepository;
import com.zproject.managment.repository.UserProjectRepository;
import com.zproject.managment.repository.UserRepository;
import com.zproject.managment.services.UserProjectService;

@Service
public class UserProjectServiceImpl implements UserProjectService {

	@Autowired
	private UserProjectRepository userProjectRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
    public Long add(UserProject userProject) {
        UserProject savedUserProject = userProjectRepository.save(userProject);
        return savedUserProject.getId();
    }

    @Override
    public void delete(Long id) {
        if (!userProjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Relation User - project with id = " + id + " not found");
        }
        userProjectRepository.deleteById(id);
    }

    @Override
    public List<User> getUsersByProfile(Long profileId) {
        profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile with id = " + profileId + " not found"));
        
        List<UserProject> userProjects = userProjectRepository.findByProfile(profileId);
        
        List<User> userList = new ArrayList<User>();
        
        for (UserProject userProject : userProjects) {
            User user = userProject.getUser();
            if (user != null) {
                userList.add(user);
            }
        }
        
        return userList;
    }

    @Override
    public List<User> getUsersByProject(Long projectId) {
    	projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project with id = " + projectId + " not found"));
    	
    	List<UserProject> userProjects = userProjectRepository.findByProject(projectId);

        List<User> users = new ArrayList<>();
        for (UserProject userProject : userProjects) {
            User user = userProject.getUser();
            if (user != null) {
                users.add(user);
            }
        }

        return users;
    }

    @Override
    public List<Project> getProjectsByUser(Long userId) {
    	userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User with id = " + userId + " not found"));
    	
    	List<UserProject> userProjects = userProjectRepository.findByUser(userId);

        List<Project> projects = new ArrayList<>();
        for (UserProject userProject : userProjects) {
            Project project = userProject.getProject();
            if (project != null) {
                projects.add(project);
            }
        }

        return projects;
    }

}
