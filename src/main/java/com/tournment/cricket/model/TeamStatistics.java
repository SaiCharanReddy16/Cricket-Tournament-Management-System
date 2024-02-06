package com.tournment.cricket.model;

import javax.persistence.*;

@Entity
@Table(name = "team_statistics")
public class TeamStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "total_matches_played")
    private Integer totalMatchesPlayed;

    @Column(name = "total_matches_won")
    private Integer totalMatchesWon;

    @Column(name = "total_matches_lost")
    private Integer totalMatchesLost;

    @Column(name = "total_matches_drawn")
    private Integer totalMatchesDrawn;

    @Column(name = "total_runs_scored")
    private Integer totalRunsScored;

    @Column(name = "total_wickets_taken")
    private Integer totalWicketsTaken;

    @Column(name = "total_catches_taken")
    private Integer totalCatchesTaken;

    // constructors, getters, setters
}

