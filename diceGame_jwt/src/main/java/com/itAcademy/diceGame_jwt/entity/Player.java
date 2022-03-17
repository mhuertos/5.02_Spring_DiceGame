package com.itAcademy.diceGame_jwt.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="players")
public class Player {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idPlayer;
	
	@Column
	private String name;
	
	@Column
	private Date registration_date = new Date();
	
	private double winningRate;
	
	@OneToMany(mappedBy="player")
	private List<Thrown> throwings = new ArrayList<Thrown>();
	

	public Player() {
	}
	
	public Player(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Long idPlayer) {
		this.idPlayer = idPlayer;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	
	
	/*public List<Thrown> getThrowings() {
		return throwings;
	}*/

	public void setThrowings(List<Thrown> throwings) {
		this.throwings = throwings;
	}
	

	public double getWinningRate() {
		return winningRate;
	}

	public void setWinningRate(double winningRate) {
		this.winningRate = winningRate;
	}

	@Override
	public String toString() {
		return "Player [idPlayer=" + idPlayer + ", name=" + name + ", registration_date=" + registration_date + "]";
	}

	
	
}
