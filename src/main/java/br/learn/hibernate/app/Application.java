package br.learn.hibernate.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.learn.hibernate.app.entity.Airport;
import br.learn.hibernate.app.entity.Passenger;
import br.learn.hibernate.app.entity.Ticket;

public class Application {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-samples");
		
		EntityManager entityManager = emf.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();

			Airport airport = new Airport(1, "Schipol");
			
			Passenger alpha = new Passenger(1, "Alpha");
			alpha.setAirport(airport);
			
			Passenger beta = new Passenger(2, "Beta");
			beta.setAirport(airport);
			
			airport.addPassenger(alpha);
			airport.addPassenger(beta);
			
			Ticket alphaTkt = new Ticket(1, "TKT123");
			alphaTkt.setPassenger(alpha);
			
			alpha.addTicket(alphaTkt);
			
			Ticket betaTkt = new Ticket(2, "TKT456");
			betaTkt.setPassenger(beta);
			
			beta.addTicket(betaTkt);
			
			entityManager.persist(airport);
			entityManager.persist(alpha);
			entityManager.persist(beta);
			entityManager.persist(alphaTkt);
			entityManager.persist(betaTkt);
			
			entityManager.getTransaction().commit();
		} finally {
			emf.close();
		}
	}

}
