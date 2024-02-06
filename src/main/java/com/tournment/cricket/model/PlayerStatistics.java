package com.tournment.cricket.model;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "total_matches_played")
    private Integer totalMatchesPlayed;

    @Column(name = "total_runs_scored")
    private Integer totalRunsScored;

    @Column(name = "total_wickets_taken")
    private Integer totalWicketsTaken;

    @Column(name = "total_catches_taken")
    private Integer totalCatchesTaken;

    public PlayerStatistics() {
    }

    public PlayerStatistics(Player player, Integer totalMatchesPlayed, Integer totalRunsScored, Integer totalWicketsTaken, Integer totalCatchesTaken) {
        this.player = player;
        this.totalMatchesPlayed = totalMatchesPlayed;
        this.totalRunsScored = totalRunsScored;
        this.totalWicketsTaken = totalWicketsTaken;
        this.totalCatchesTaken = totalCatchesTaken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getTotalMatchesPlayed() {
        return totalMatchesPlayed;
    }

    public void setTotalMatchesPlayed(Integer totalMatchesPlayed) {
        this.totalMatchesPlayed = totalMatchesPlayed;
    }

    public Integer getTotalRunsScored() {
        return totalRunsScored;
    }

    public void setTotalRunsScored(Integer totalRunsScored) {
        this.totalRunsScored = totalRunsScored;
    }

    public Integer getTotalWicketsTaken() {
        return totalWicketsTaken;
    }

    public void setTotalWicketsTaken(Integer totalWicketsTaken) {
        this.totalWicketsTaken = totalWicketsTaken;
    }

    public Integer getTotalCatchesTaken() {
        return totalCatchesTaken;
    }

    public void setTotalCatchesTaken(Integer totalCatchesTaken) {
        this.totalCatchesTaken = totalCatchesTaken;
    }
}
