package com.zproject.managment.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zproject.managment.model.Project;
import com.zproject.managment.model.Workpackage;
import com.zproject.managment.repository.ProjectRepository;
import com.zproject.managment.repository.WorkpackageRepository;
import com.zproject.managment.services.WorkpackageService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WorkpackageServiceImpl implements WorkpackageService{


	@Autowired
	WorkpackageRepository workpackageRepository;

	@Autowired
	ProjectRepository projectsRepository;
	
	
	private void ensureProjectExists(Long projectId) {
        if (!this.projectsRepository.existsById(projectId)) {
            throw new EntityNotFoundException("Project with id=" + projectId + " not found");
        }
    }
	
	@Override
	public List<Workpackage> getAllByProject(Long project_id) {
		this.ensureProjectExists(project_id);
		return workpackageRepository.findByProject_Id(project_id);
	}

	@Override
    public Integer getTotalHours(Long project_id) {
        ensureProjectExists(project_id);
        return workpackageRepository.sumHoursByProject_Id(project_id);
    }

    @Override
    public Long save(Workpackage workpackage) {
        Long projId = Optional.ofNullable(workpackage.getProject())
                              .map(Project::getId)
                              .orElseThrow(() ->
                                  new IllegalArgumentException("Workpackage must reference a project"));
        ensureProjectExists(projId);
        Workpackage saved = workpackageRepository.save(workpackage);
        return saved.getId();
    }

    @Override
    public Workpackage edit(Workpackage workpackage) {
        Long wpId = workpackage.getId();
        if (wpId == null) {
            throw new IllegalArgumentException("Workpackage ID must be provided for edit");
        }

        Workpackage existing = workpackageRepository.findById(wpId)
            .orElseThrow(() ->
                new EntityNotFoundException("Workpackage with id=" + wpId + " not found"));

        existing.setName(workpackage.getName());
        existing.setDescription(workpackage.getDescription());
        existing.setHours(workpackage.getHours());
        existing.setStart_dt(workpackage.getStart_dt());
        existing.setEnd_dt(workpackage.getEnd_dt());

        if (workpackage.getProject() != null) {
            Long newProjId = workpackage.getProject().getId();
            ensureProjectExists(newProjId);
            existing.setProject(workpackage.getProject());
        }

        return workpackageRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!workpackageRepository.existsById(id)) {
            throw new EntityNotFoundException("Workpackage with id=" + id + " not found");
        }
        workpackageRepository.deleteById(id);
    }

}
