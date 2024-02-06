package com.tournment.cricket.model;

import javax.persistence.*;

@Entity
@Table(name = "match_result")
public class MatchResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "winning_team_id")
    private Team winningTeam;

    @ManyToOne
    @JoinColumn(name = "losing_team_id")
    private Team losingTeam;

    @Column(name = "result_type")
    private String resultType;

    public MatchResult() {
    }

    public MatchResult(Match match, Team winningTeam, Team losingTeam, String resultType) {
        this.match = match;
        this.winningTeam = winningTeam;
        this.losingTeam = losingTeam;
        this.resultType = resultType;
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

    public Team getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(Team winningTeam) {
        this.winningTeam = winningTeam;
    }

    public Team getLosingTeam() {
        return losingTeam;
    }

    public void setLosingTeam(Team losingTeam) {
        this.losingTeam = losingTeam;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
