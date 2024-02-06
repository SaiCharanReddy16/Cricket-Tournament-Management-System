package com.tournment.cricket.service;

/**
 * Created by DESIREDDY JAYASYAM
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.tournment.cricket.model.Tournament;
import com.tournment.cricket.repository.TournamentRepository;

@RunWith(MockitoJUnitRunner.class)
public class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private TournamentService tournamentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTournments() {
        Tournament tournament1 = new Tournament();
        tournament1.setId(1L);
        tournament1.setName("IPL");
        tournament1.setLocation("India");
        tournament1.setStartDate(LocalDate.of(2022, 4, 1));
        tournament1.setEndDate(LocalDate.of(2022, 5, 30));


        Tournament tournament2 = new Tournament();
        tournament2.setId(2L);
        tournament2.setName("BBL");
        tournament2.setLocation("Australia");
        tournament2.setStartDate(LocalDate.of(2022, 12, 1));
        tournament2.setEndDate(LocalDate.of(2023, 1, 30));

        List<Tournament> tournamentList = Arrays.asList(tournament1, tournament2);

        when(tournamentRepository.findAll()).thenReturn(tournamentList);

        List<Tournament> result = tournamentService.getAllTournments();

        assertEquals(tournamentList, result);
    }

    @Test
    public void testCreateTournament() {
        Tournament tournament = new Tournament();
        tournament.setId(1L);
        tournament.setName("IPL");
        tournament.setLocation("India");
        tournament.setStartDate(LocalDate.of(2022, 4, 1));
        tournament.setEndDate(LocalDate.of(2022, 5, 30));

        when(tournamentRepository.save(tournament)).thenReturn(tournament);

        Tournament result = tournamentService.createTournament(tournament);

        assertNotNull(result);
        assertEquals(tournament, result);
    }

    @Test
    public void testGetTournamentById() {
        Tournament tournament = new Tournament();
        tournament.setId(1L);
        tournament.setName("IPL");
        tournament.setLocation("India");
        tournament.setStartDate(LocalDate.of(2022, 4, 1));
        tournament.setEndDate(LocalDate.of(2022, 5, 30));

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));

        Tournament result = tournamentService.getTournamentById(1L);

        assertEquals(tournament, result);
    }

    @Test
    public void testGetTournamentByIdNotFound() {
        when(tournamentRepository.findById(1L)).thenReturn(Optional.empty());

        Tournament result = tournamentService.getTournamentById(1L);

        assertNull(result);
    }

    @Test
    public void testUpdateTournament() {
        Tournament existingTournament = new Tournament();
        existingTournament.setId(1L);
        existingTournament.setName("IPL");
        existingTournament.setLocation("India");
        existingTournament.setStartDate(LocalDate.of(2022, 4, 1));
        existingTournament.setEndDate(LocalDate.of(2022, 5, 30));

        Tournament updatedTournament = new Tournament();
        updatedTournament.setId(1L);
        updatedTournament.setName("IPL 2022");
        updatedTournament.setLocation("India");
        updatedTournament.setStartDate(LocalDate.of(2022, 4, 1));
        updatedTournament.setEndDate(LocalDate.of(2022, 6, 5));

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(existingTournament));
        when(tournamentRepository.save(existingTournament)).thenReturn(updatedTournament);

        Tournament result = tournamentService.updateTournament(1L, updatedTournament);

        assertNotNull(result);
        assertEquals(updatedTournament, result);
    }

    @Test
    public void testUpdateTournamentNotFound() {
        Tournament updatedTournament = new Tournament();
        updatedTournament.setId(1L);
        updatedTournament.setName("IPL 2022");
        updatedTournament.setLocation("India");
        updatedTournament.setStartDate(LocalDate.of(2022, 4, 1));
        updatedTournament.setEndDate(LocalDate.of(2022, 6, 5));

        when(tournamentRepository.findById(1L)).thenReturn(Optional.empty());

        Tournament result = tournamentService.updateTournament(1L, updatedTournament);

        assertNull(result);
    }

    @Test
    public void testDeleteTournament() {
        tournamentService.deleteTournament(1L);

        // verify that the deleteById method of the tournamentRepository was called with the correct id
        verify(tournamentRepository, times(1)).deleteById(1L);
    }
}
