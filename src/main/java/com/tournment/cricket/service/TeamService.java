package com.tournment.cricket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tournment.cricket.model.Team;
import com.tournment.cricket.repository.TeamRepository;

@Service
public class TeamService {
    @Autowired
    private TeamRepository repository;

    public List<Team> getAllTeams() {
        return repository.findAll();
    }

    public Team getTeamById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Team createTeam(Team team) {
        return repository.save(team);
    }

    public Team updateTeam(Long id, Team team) {
        Team existingTeam = repository.findById(id).orElse(null);
        if (existingTeam != null) {
        	if(team.getName()!=null) {
            existingTeam.setName(team.getName());
        	}
        	
            return repository.save(existingTeam);
        }
        return null;
    }

    public void deleteTeam(Long id) {
        repository.deleteById(id);
    }

	public Team getTeamCoachesByTeamId(Long teamId) {
		return repository.getTeamCoachesByTeamId(teamId);
	}

	public void updateTeamCoach(Long teamId, Long coachId) {
		repository.updateTeamCoach(teamId,coachId);
		
	}

	public void updateCoach(Long teamId, Long coachId) {
		repository.updateCoach(teamId,coachId);
			
	}
}

