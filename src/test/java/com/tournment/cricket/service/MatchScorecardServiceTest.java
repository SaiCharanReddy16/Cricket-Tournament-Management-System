package com.tournment.cricket.service;

/**
 * Created by DESIREDDY JAYASYAM
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
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

import com.tournment.cricket.model.Match;
import com.tournment.cricket.model.MatchScorecard;
import com.tournment.cricket.model.MatchScorecardDetails;
import com.tournment.cricket.model.Team;
import com.tournment.cricket.repository.MatchRepository;
import com.tournment.cricket.repository.MatchScorecardDetailsRepository;
import com.tournment.cricket.repository.MatchScorecardRepository;
import com.tournment.cricket.repository.TeamRepository;

@RunWith(MockitoJUnitRunner.class)
public class MatchScorecardServiceTest {

    @Mock
    private MatchScorecardRepository matchScorecardRepository;

    @Mock
    private MatchScorecardDetailsRepository matchScorecardDetailsRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchScorecardService matchScorecardService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllMatchScorecards() {
        Match match = new Match();
        match.setId(1L);

        Team team = new Team();
        team.setId(1L);

        MatchScorecard matchScorecard1 = new MatchScorecard();
        matchScorecard1.setId(1L);
        matchScorecard1.setMatch(match);
        matchScorecard1.setTeam(team);
        matchScorecard1.setTotalRuns(100);
        matchScorecard1.setTotalWickets(5);
        matchScorecard1.setTotalOvers(20.0f);

        MatchScorecard matchScorecard2 = new MatchScorecard();
        matchScorecard2.setId(2L);
        matchScorecard2.setMatch(match);
        matchScorecard2.setTeam(team);
        matchScorecard2.setTotalRuns(150);
        matchScorecard2.setTotalWickets(7);
        matchScorecard2.setTotalOvers(25.0f);

        List<MatchScorecard> matchScorecardList = Arrays.asList(matchScorecard1, matchScorecard2);

        when(matchScorecardRepository.findAll()).thenReturn(matchScorecardList);

        List<MatchScorecard> result = matchScorecardService.getAllMatchScorecards();

        assertEquals(matchScorecardList, result);
    }

    @Test
    public void testGetMatchScorecardById() {
        Match match = new Match();
        match.setId(1L);

        Team team = new Team();
        team.setId(1L);

        MatchScorecard matchScorecard = new MatchScorecard();
        matchScorecard.setId(1L);
        matchScorecard.setMatch(match);
        matchScorecard.setTeam(team);
        matchScorecard.setTotalRuns(100);
        matchScorecard.setTotalWickets(5);
        matchScorecard.setTotalOvers(20.0f);

        when(matchScorecardRepository.findById(1L)).thenReturn(Optional.of(matchScorecard));

        MatchScorecard result = matchScorecardService.getMatchScorecardById(1L);

        assertEquals(matchScorecard, result);
    }

    @Test
    public void testGetMatchScorecardById_NotFound() {
        when(matchScorecardRepository.findById(1L)).thenReturn(Optional.empty());
        MatchScorecard result = matchScorecardService.getMatchScorecardById(1L);

        assertNull(result);
    }

    @Test
    public void testCreateMatchScorecard() {
        Match match = new Match();
        match.setId(1L);

        Team team = new Team();
        team.setId(1L);

        MatchScorecard matchScorecard = new MatchScorecard();
        matchScorecard.setId(1L);
        matchScorecard.setMatch(match);
        matchScorecard.setTeam(team);
        matchScorecard.setTotalRuns(100);
        matchScorecard.setTotalWickets(5);
        matchScorecard.setTotalOvers(20.0f);

        when(matchScorecardRepository.save(any())).thenReturn(matchScorecard);

        MatchScorecard result = matchScorecardService.createMatchScorecard(matchScorecard);

        assertNotNull(result);
        assertEquals(matchScorecard, result);
    }

    @Test
    public void testUpdateMatchScorecard() {
        Match match = new Match();
        match.setId(1L);

        Team team = new Team();
        team.setId(1L);

        MatchScorecard existingMatchScorecard = new MatchScorecard();
        existingMatchScorecard.setId(1L);
        existingMatchScorecard.setMatch(match);
        existingMatchScorecard.setTeam(team);
        existingMatchScorecard.setTotalRuns(100);
        existingMatchScorecard.setTotalWickets(5);
        existingMatchScorecard.setTotalOvers(20.0f);

        MatchScorecard updatedMatchScorecard = new MatchScorecard();
        updatedMatchScorecard.setId(1L);
        updatedMatchScorecard.setMatch(match);
        updatedMatchScorecard.setTeam(team);
        updatedMatchScorecard.setTotalRuns(150);
        updatedMatchScorecard.setTotalWickets(7);
        updatedMatchScorecard.setTotalOvers(25.0f);

        when(matchScorecardRepository.findById(1L)).thenReturn(Optional.of(existingMatchScorecard));
        when(matchScorecardRepository.save(any())).thenReturn(updatedMatchScorecard);

        MatchScorecard result = matchScorecardService.updateMatchScorecard(1L, updatedMatchScorecard);

        assertNotNull(result);
        assertEquals(updatedMatchScorecard, result);
    }

    @Test
    public void testUpdateMatchScorecard_NotFound() {
        MatchScorecard updatedMatchScorecard = new MatchScorecard();
        updatedMatchScorecard.setId(1L);

        when(matchScorecardRepository.findById(1L)).thenReturn(Optional.empty());

        MatchScorecard result = matchScorecardService.updateMatchScorecard(1L, updatedMatchScorecard);

        assertNull(result);
    }

    @Test
    public void testDeleteMatchScorecard() {
        matchScorecardService.deleteMatchScorecard(1L);

        // verify that the deleteById method was called once
        verify(matchScorecardRepository, times(1)).deleteById(1L);
    }
}

