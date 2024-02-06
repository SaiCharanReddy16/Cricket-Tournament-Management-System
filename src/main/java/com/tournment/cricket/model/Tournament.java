package com.tournment.cricket.model;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tournament")
public class Tournament {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private TournamentOrganizer organizer;

    // constructors, getters, setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public TournamentOrganizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(TournamentOrganizer organizer) {
		this.organizer = organizer;
	}

}
