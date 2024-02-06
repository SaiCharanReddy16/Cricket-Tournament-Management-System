package com.tournment.cricket.model;

import javax.persistence.*;

@Entity
@Table(name = "points_table")
public class PointsTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

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

    @Column(name = "total_points")
    private Integer totalPoints;

    // constructors, getters, setters
}



