package com.tournment.cricket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tournment.cricket.model.Player;
import com.tournment.cricket.model.PlayerStatistics;
import com.tournment.cricket.repository.MatchRepository;
import com.tournment.cricket.repository.PlayerRepository;
import com.tournment.cricket.repository.PlayerStatisticsRepository;

@Service
public class PlayerStatisticsService {
    @Autowired
    private PlayerStatisticsRepository playerStatisticsRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

    public List<PlayerStatistics> getAllPlayerStatistics() {
        return playerStatisticsRepository.findAll();
    }

    public PlayerStatistics getPlayerStatisticsById(Long id) {
        return playerStatisticsRepository.findById(id).orElse(null);
    }

    public PlayerStatistics createPlayerStatistics(PlayerStatistics playerStatistics) {
        if (playerStatistics.getPlayer() != null) {
            Long playerId = playerStatistics.getPlayer().getId();
            Player player = playerRepository.findById(playerId).orElse(null);
            playerStatistics.setPlayer(player);
        }

        return playerStatisticsRepository.save(playerStatistics);
    }

    public PlayerStatistics updatePlayerStatistics(Long id, PlayerStatistics playerStatistics) {
        PlayerStatistics existingPlayerStatistics = playerStatisticsRepository.findById(id).orElse(null);
        if (existingPlayerStatistics != null) {
            existingPlayerStatistics.setTotalRunsScored(playerStatistics.getTotalRunsScored());
            existingPlayerStatistics.setTotalWicketsTaken(playerStatistics.getTotalWicketsTaken());
            existingPlayerStatistics.setTotalCatchesTaken(playerStatistics.getTotalCatchesTaken());
            existingPlayerStatistics.setPlayer(playerStatistics.getPlayer());
            return playerStatisticsRepository.save(existingPlayerStatistics);
        }
        return null;
    }

    public void deletePlayerStatistics(Long id) {
        playerStatisticsRepository.deleteById(id);
    }
}

