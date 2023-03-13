package br.learn.hibernate.app.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PASSENGERS")
@Access(AccessType.FIELD)
public class Passenger {

	public Passenger() {
	}

	public Passenger(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "AIRPORT_ID")
	private Airport airport;

	@OneToMany(mappedBy = "passenger")
	List<Ticket> tickets = new ArrayList<Ticket>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}
	
	public void addTicket(Ticket ticket) {
		this.tickets.add(ticket);
	}
	
	public List<Ticket> getTickets() {
		return Collections.unmodifiableList(this.tickets);
	}

}
