package com.tournment.cricket.service;

/**
 * Created by DESIREDDY JAYASYAM
 */

import com.tournment.cricket.model.Player;
import com.tournment.cricket.model.PlayerStatistics;
import com.tournment.cricket.repository.MatchRepository;
import com.tournment.cricket.repository.PlayerRepository;
import com.tournment.cricket.repository.PlayerStatisticsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerStatisticsServiceTest {

    @Mock
    private PlayerStatisticsRepository playerStatisticsRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private PlayerStatisticsService playerStatisticsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private Player createMockPlayer(Long id, String name, String email) {
        Player player = new Player();
        player.setId(id);
        return player;
    }

    private PlayerStatistics createMockPlayerStatistics(Long id, Player player, Integer totalRunsScored,
                                                        Integer totalWicketsTaken, Integer totalCatchesTaken) {
        PlayerStatistics playerStatistics = new PlayerStatistics();
        playerStatistics.setId(id);
        playerStatistics.setPlayer(player);
        playerStatistics.setTotalRunsScored(totalRunsScored);
        playerStatistics.setTotalWicketsTaken(totalWicketsTaken);
        playerStatistics.setTotalCatchesTaken(totalCatchesTaken);
        return playerStatistics;
    }

    @Test
    public void testGetAllPlayerStatistics() {
        Player player1 = createMockPlayer(1L, "Player 1", "player1@example.com");
        Player player2 = createMockPlayer(2L, "Player 2", "player2@example.com");

        PlayerStatistics playerStatistics1 = createMockPlayerStatistics(1L, player1, 500, 10, 5);
        PlayerStatistics playerStatistics2 = createMockPlayerStatistics(2L, player2, 250, 5, 2);

        List<PlayerStatistics> playerStatisticsList = Arrays.asList(playerStatistics1, playerStatistics2);

        when(playerStatisticsRepository.findAll()).thenReturn(playerStatisticsList);

        List<PlayerStatistics> result = playerStatisticsService.getAllPlayerStatistics();

        assertEquals(playerStatisticsList, result);
    }

    @Test
    public void testGetPlayerStatisticsById() {
        Player player = createMockPlayer(1L, "Player 1", "player1@example.com");
        PlayerStatistics playerStatistics = createMockPlayerStatistics(1L, player, 500, 10, 5);

        when(playerStatisticsRepository.findById(1L)).thenReturn(Optional.of(playerStatistics));

        PlayerStatistics result = playerStatisticsService.getPlayerStatisticsById(1L);

        assertEquals(playerStatistics, result);
    }

    @Test
    public void testCreatePlayerStatistics() {
        Player player = createMockPlayer(1L, "Player 1", "player1@example.com");
        PlayerStatistics playerStatistics = createMockPlayerStatistics(null, player, 500, 10, 5);

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        when(playerStatisticsRepository.save(any(PlayerStatistics.class))).thenReturn(playerStatistics);

        PlayerStatistics result = playerStatisticsService.createPlayerStatistics(playerStatistics);
        assertEquals(playerStatistics, result);
    }

    @Test
    public void testUpdatePlayerStatistics() {
        Player player = createMockPlayer(1L, "Player 1", "player1@example.com");
        PlayerStatistics existingPlayerStatistics = createMockPlayerStatistics(1L, player, 500, 10, 5);
        PlayerStatistics updatedPlayerStatistics = createMockPlayerStatistics(1L, player, 1000, 15, 10);

        when(playerStatisticsRepository.findById(1L)).thenReturn(Optional.of(existingPlayerStatistics));
        when(playerStatisticsRepository.save(existingPlayerStatistics)).thenReturn(updatedPlayerStatistics);

        PlayerStatistics result = playerStatisticsService.updatePlayerStatistics(1L, updatedPlayerStatistics);

        assertEquals(updatedPlayerStatistics, result);
    }

    @Test
    public void testDeletePlayerStatistics() {
        playerStatisticsService.deletePlayerStatistics(1L);

        verify(playerStatisticsRepository, times(1)).deleteById(1L);
    }
}
