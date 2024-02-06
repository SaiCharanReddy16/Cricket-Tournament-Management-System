package com.tournment.cricket.service;

/**
 * Created by DESIREDDY JAYASYAM
 */

import com.tournment.cricket.model.Player;
import com.tournment.cricket.model.Team;
import com.tournment.cricket.repository.CaptainRepository;
import com.tournment.cricket.repository.PlayerRepository;
import com.tournment.cricket.repository.TeamRepository;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private CaptainRepository captainRepository;

    @InjectMocks
    private PlayerService playerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private Player createMockPlayer(Long id, String firstName, String lastName, String country, String position, Integer jerseyNumber, Team team) {
        Player player = new Player();
        player.setId(id);
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setCountry(country);
        player.setPosition(position);
        player.setJerseyNumber(jerseyNumber);
        player.setTeam(team);
        return player;
    }

    private Team createMockTeam(Long id, String name, String city, String state, String country) {
        Team team = new Team();
        team.setId(id);
        team.setName(name);
        return team;
    }

    @Test
    public void testGetAllPlayers() {
        Team team1 = createMockTeam(1L, "Team 1", "City 1", "State 1", "Country 1");
        Team team2 = createMockTeam(2L, "Team 2", "City 2", "State 2", "Country 2");

        Player player1 = createMockPlayer(1L, "Player 1", "Last Name 1", "Country 1", "Position 1", 10, team1);
        Player player2 = createMockPlayer(2L, "Player 2", "Last Name 2", "Country 2", "Position 2", 20, team2);

        List<Player> playerList = Arrays.asList(player1, player2);

        when(playerRepository.findAll()).thenReturn(playerList);

        List<Player> result = playerService.getAllPlayers();

        assertEquals(playerList, result);
    }

    @Test
    public void testGetPlayerById() {
        Team team = createMockTeam(1L, "Team 1", "City 1", "State 1", "Country 1");
        Player player = createMockPlayer(1L, "Player 1", "Last Name 1", "Country 1", "Position 1", 10, team);

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));

        Player result = playerService.getPlayerById(1L);

        assertEquals(player, result);
    }

    @Test
    public void testCreatePlayer() {
        Team team = createMockTeam(1L, "Team 1", "City 1", "State 1", "Country 1");
        Player player = createMockPlayer(null, "First Name", "Last Name", "Country", "Position", 7, team);
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        Player result = playerService.createPlayer(player);

        assertNotNull(result);
        assertEquals(player.getFirstName(), result.getFirstName());
        assertEquals(player.getLastName(), result.getLastName());
        assertEquals(player.getCountry(), result.getCountry());
        assertEquals(player.getPosition(), result.getPosition());
        assertEquals(player.getJerseyNumber(), result.getJerseyNumber());
        assertEquals(player.getTeam(), result.getTeam());
    }

    @Test
    public void testUpdatePlayer() {
        Team team = createMockTeam(1L, "Team 1", "City 1", "State 1", "Country 1");
        Player existingPlayer = createMockPlayer(1L, "First Name", "Last Name", "Country", "Position",7, team);
        Player updatedPlayer = createMockPlayer(1L, "First Name Updated", "Last Name Updated", "Country Updated", "Position Updated", 7, team);

        when(playerRepository.findById(1L)).thenReturn(Optional.of(existingPlayer));
        when(playerRepository.save(existingPlayer)).thenReturn(updatedPlayer);

        Player result = playerService.updatePlayer(1L, updatedPlayer);

        assertNotNull(result);
        assertEquals(updatedPlayer.getFirstName(), result.getFirstName());
        assertEquals(updatedPlayer.getLastName(), result.getLastName());
        assertEquals(updatedPlayer.getCountry(), result.getCountry());
        assertEquals(updatedPlayer.getPosition(), result.getPosition());
        assertEquals(updatedPlayer.getJerseyNumber(), result.getJerseyNumber());
        assertEquals(updatedPlayer.getTeam(), result.getTeam());
    }

    @Test
    public void testDeletePlayer() {
        Long playerId = 1L;

        doNothing().when(playerRepository).deleteById(playerId);

        playerService.deletePlayer(playerId);

        verify(playerRepository, times(1)).deleteById(playerId);
    }

    @Test
    public void testGetPlayersByTeamId() {
        Team team = createMockTeam(1L, "Team 1", "City 1", "State 1", "Country 1");
        Player player1 = createMockPlayer(1L, "First Name 1", "Last Name 1", "Country 1", "Position 1", 7, team);
        Player player2 = createMockPlayer(2L, "First Name 2", "Last Name 2", "Country 2", "Position 2", 7, team);
        List<Player> playerList = Arrays.asList(player1, player2);

        when(playerRepository.findByTeamId(1L)).thenReturn(playerList);

        List<Player> result = playerService.getPlayersByTeamId(1L);

        assertEquals(playerList, result);
    }

    @Test
    public void testExchangePlayer() {
        Long playerId = 1L;
        Long newTeamId = 2L;

        doNothing().when(playerRepository).exchangePlayer(playerId, newTeamId);

        playerService.exchangePlayer(playerId, newTeamId);

        verify(playerRepository, times(1)).exchangePlayer(playerId, newTeamId);
    }
}
