package br.learn.hibernate.app.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AIRPORTS")
@Access(AccessType.FIELD)
public class Airport {

	public Airport() {
	}

	public Airport(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;
	
	@OneToMany(mappedBy = "airport")
	private List<Passenger> passengers = new ArrayList<Passenger>();

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
	
	public void addPassenger(Passenger passenger) {
		this.passengers.add(passenger);
	}
	
	public List<Passenger> getPassengers() {
		return Collections.unmodifiableList(this.passengers);
	}

}
