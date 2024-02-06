package com.tournment.cricket.model;

import javax.persistence.*;

@Entity
@Table(name = "captain")
public class Captain {
    @Override
	public String toString() {
		return "Captain [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", team=" + team
				+ "]";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Captain() {
    }

    public Captain(String name, String email, String phone, Team team) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.team = team;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
