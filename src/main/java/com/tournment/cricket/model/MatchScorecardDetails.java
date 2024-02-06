package com.tournment.cricket.model;

import javax.persistence.*;

@Entity
@Table(name = "match_scorecard_details")
public class MatchScorecardDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scorecard_id")
    private MatchScorecard scorecard;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "runs_scored")
    private Integer runsScored;

    @Column(name = "balls_faced")
    private Integer ballsFaced;

    @Column(name = "wickets_taken")
    private Integer wicketsTaken;

    public Integer getExtrasGiven() {
		return extrasGiven;
	}

	public void setExtrasGiven(Integer extrasGiven) {
		this.extrasGiven = extrasGiven;
	}

	public String getDismissalType() {
		return dismissalType;
	}

	public void setDismissalType(String dismissalType) {
		this.dismissalType = dismissalType;
	}

	@Column(name = "overs_bowled")
    private Float oversBowled;

    @Column(name = "extras_given")
    private Integer extrasGiven;

    @Column(name = "dismissal_type")
    private String dismissalType;

    public MatchScorecardDetails() {
    }

    public MatchScorecardDetails(MatchScorecard scorecard, Player player, Integer runsScored, Integer ballsFaced, Integer wicketsTaken, Float oversBowled, Integer extrasGiven, String dismissalType) {
        this.scorecard = scorecard;
        this.player = player;
        this.runsScored = runsScored;
        this.ballsFaced = ballsFaced;
        this.wicketsTaken = wicketsTaken;
        this.oversBowled = oversBowled;
        this.extrasGiven = extrasGiven;
        this.dismissalType = dismissalType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MatchScorecard getScorecard() {
        return scorecard;
    }

    public void setScorecard(MatchScorecard scorecard) {
        this.scorecard = scorecard;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getRunsScored() {
        return runsScored;
    }

    @Override
	public String toString() {
		return "MatchScorecardDetails [id=" + id + ", scorecard=" + scorecard + ", player=" + player + ", runsScored="
				+ runsScored + ", ballsFaced=" + ballsFaced + ", wicketsTaken=" + wicketsTaken + ", oversBowled="
				+ oversBowled + ", extrasGiven=" + extrasGiven + ", dismissalType=" + dismissalType + "]";
	}

	public void setRunsScored(Integer runsScored) {
        this.runsScored = runsScored;
    }

    public Integer getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(Integer ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public Integer getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(Integer wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public Float getOversBowled() {
        return oversBowled;
    }

    public void setOversBowled(Float oversBowled) {
        this.oversBowled = oversBowled;
    }}
