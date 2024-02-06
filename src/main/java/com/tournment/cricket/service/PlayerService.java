package com.tournment.cricket.service;

import com.tournment.cricket.model.Captain;
import com.tournment.cricket.model.Player;
import com.tournment.cricket.model.Team;
import com.tournment.cricket.repository.CaptainRepository;
import com.tournment.cricket.repository.PlayerRepository;
import com.tournment.cricket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CaptainRepository captainRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player createPlayer(Player player) {
        if (player.getTeam() != null) {
            Long teamId = player.getTeam().getId();
            Team team = teamRepository.findById(teamId).orElse(null);
            player.setTeam(team);
        }

        return playerRepository.save(player);
    }

    public Player updatePlayer(Long id, Player player) {
        Player existingPlayer = playerRepository.findById(id).orElse(null);
        if (existingPlayer != null) {
        	if(player.getFirstName()!=null) {
            existingPlayer.setFirstName(player.getFirstName());
        	}
        	if(player.getLastName()!=null) {
                
            existingPlayer.setLastName(player.getLastName());
        	}
        	if(player.getCountry()!=null) {
            existingPlayer.setCountry(player.getCountry());
        	}
        	if(player.getPosition()!=null) {
            existingPlayer.setPosition(player.getPosition());
        	}
        	if(player.getJerseyNumber()!=null) {
            existingPlayer.setJerseyNumber(player.getJerseyNumber());
        	}
        	if(player.getTeam()!=null) {
            existingPlayer.setTeam(player.getTeam());
        	}
        	return playerRepository.save(existingPlayer);
        }
        return null;
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

	public List<Player> getPlayersByTeamId(Long teamId) {
		
		return playerRepository.findByTeamId(teamId);
	}

	public void exchangePlayer(Long id, Long newTeamId) {

		 playerRepository.exchangePlayer(id,newTeamId);
	}

	public void makeAsCaptain(Long id, Long teamId) {
		 Optional<Player> playerOpt =  playerRepository.findById(id);
		List<Captain>captains=  captainRepository.findByTeamId(teamId);
		Long captainId =null;
		if(playerOpt.isPresent() ) {
		Player player = playerOpt.get();
		if(captains!=null && !captains.isEmpty() &&  player!=null) {
			captainId=captains.get(0).getId();
			captainRepository.updateCaptain( captainId,player.getFirstName()+" "+player.getLastName()); 
		}else {
			  captainRepository.insertCaptain(teamId ,player.getFirstName()+" "+player.getLastName()); 
			List<Captain> c = captainRepository.findByTeamId(teamId);	
			if(captains!=null && !captains.isEmpty() ) {
				captainId=c.get(0).getId();
			}
		}
		playerRepository.updateTeamCaptain(teamId, captainId);

		}

		
	}
}

