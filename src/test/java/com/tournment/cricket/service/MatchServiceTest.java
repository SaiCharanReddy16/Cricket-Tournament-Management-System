package com.tournment.cricket.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tournment.cricket.repository.UmpireRepository;
import com.tournment.cricket.repository.VenueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tournment.cricket.model.Match;
import com.tournment.cricket.model.Umpire;
import com.tournment.cricket.model.Venue;
import com.tournment.cricket.repository.MatchRepository;

@SpringBootTest
public class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private VenueRepository venueRepository;

    @Mock
    private UmpireRepository umpireRepository;

    @Test
    public void testGetAllMatches() {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match());
        matches.add(new Match());
        when(matchRepository.getAllMatches()).thenReturn(matches);

        List<Match> result = matchService.getAllMatches();

        assertEquals(matches, result);
    }

    @Test
    public void testGetMatchById() {
        Match match = new Match();
        match.setId(1L);
        when(matchRepository.getMatchDetails(1L)).thenReturn(match);

        Match result = matchService.getMatchById(1L);

        assertEquals(match, result);
    }

    @Test
    public void testCreateMatch() {
        Venue venue = new Venue();
        venue.setId(1L);

        Umpire umpire = new Umpire();
        umpire.setId(1L);

        Match match = new Match();
        match.setMatchDate(LocalDate.now());
        match.setMatchType("Test");
        match.setMatchTime(LocalTime.now());
        match.setVenue(venue);
        match.setUmpire(umpire);

        when(venueRepository.findById(1L)).thenReturn(Optional.of(venue));
        when(umpireRepository.findById(1L)).thenReturn(Optional.of(umpire));
        when(matchRepository.save(match)).thenReturn(match);

        Match result = matchService.createMatch(match);

        assertEquals(match, result);
    }
}
