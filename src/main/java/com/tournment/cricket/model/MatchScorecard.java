package com.tournment.cricket.model;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "match_scorecard")
public class MatchScorecard {
    	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "total_runs")
    private Integer totalRuns;

    @Column(name = "total_wickets")
    private Integer totalWickets;

    @Column(name = "total_overs")
    private Float totalOvers;

    @Column(name = "total_extras")
    private Integer totalExtras;

    @OneToMany(mappedBy = "scorecard")
    private List<MatchScorecardDetails> details;

    public MatchScorecard() {
    }

    public MatchScorecard(Match match, Team team, Integer totalRuns, Integer totalWickets, Float totalOvers, Integer totalExtras, List<MatchScorecardDetails> details) {
        this.match = match;
        this.team = team;
        this.totalRuns = totalRuns;
        this.totalWickets = totalWickets;
        this.totalOvers = totalOvers;
        this.totalExtras = totalExtras;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(Integer totalRuns) {
        this.totalRuns = totalRuns;
    }

    public Integer getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(Integer totalWickets) {
        this.totalWickets = totalWickets;
    }

    public Float getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(Float totalOvers) {
        this.totalOvers = totalOvers;
    }

    public Integer getTotalExtras() {
        return totalExtras;
    }

    public void setTotalExtras(Integer totalExtras) {
        this.totalExtras = totalExtras;
    }

    public List<MatchScorecardDetails> getDetails() {
        return details;
    }

    public void setDetails(List<MatchScorecardDetails> details) {
        this.details = details;
    }
}
