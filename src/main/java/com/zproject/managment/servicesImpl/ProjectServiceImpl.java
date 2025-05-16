package com.zproject.managment.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zproject.managment.exception.ResourceNotFoundException;
import com.zproject.managment.model.Project;
import com.zproject.managment.repository.ProjectRepository;
import com.zproject.managment.services.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
    private ProjectRepository projectRepository;
	
	@Override
	public List<Project> getAll() {
		return projectRepository.findAll();
	}

	@Override
    public Long save(Project project) {
        Project savedProject = projectRepository.save(project);
        return savedProject.getId();
    }

    @Override
    public Project edit(Project project) {
        Long id = project.getId();
        if (id == null || !projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project with id = " + id + " not found");
        }
        Project updatedProject = projectRepository.save(project);
        return updatedProject;
    }

    @Override
    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project with id = " + id + " not found");
        }
        projectRepository.deleteById(id);
    }

	@Override
	public Optional<Project> get(Long id) {
		Optional<Project> project = projectRepository.findById(id);
		return project;
	}

}
