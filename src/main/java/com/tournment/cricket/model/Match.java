package com.tournment.cricket.model;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "matchCR")
public class Match {
    public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public List<MatchScorecard> getScorecards() {
		return scorecards;
	}

	public void setScorecards(List<MatchScorecard> scorecards) {
		this.scorecards = scorecards;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @Column(name = "match_time")
    private LocalTime matchTime;

    @Column(name = "match_type")
    private String matchType;

    @ManyToOne
    @JoinColumn(name = "umpire_id")
    private Umpire umpire;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "team_1_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team_2_id")
    private Team team2;

    @OneToMany(mappedBy = "match")
    private List<MatchScorecard> scorecards;

	public Match() {
    }

    public Match(LocalDate matchDate, LocalTime matchTime, String matchType, Umpire umpire, Tournament tournament, Venue venue, Team team1, Team team2, List<MatchScorecard> scorecards) {
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.matchType = matchType;
        this.umpire = umpire;
        this.tournament = tournament;
        this.venue = venue;
        this.team1 = team1;
        this.team2 = team2;
        this.scorecards = scorecards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public LocalTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalTime matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public Umpire getUmpire() {
        return umpire;
    }

    public void setUmpire(Umpire umpire) {
        this.umpire = umpire;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue){
        this.venue= venue;
    }

}