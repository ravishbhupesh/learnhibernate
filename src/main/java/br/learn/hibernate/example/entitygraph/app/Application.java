package br.learn.hibernate.example.entitygraph.app;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.learn.hibernate.example.entitygraph.app.entity.Comment;
import br.learn.hibernate.example.entitygraph.app.entity.Post;
import br.learn.hibernate.example.entitygraph.app.entity.User;

public class Application {

	public static void main(String[] args) {

		Application app = new Application();
		//app.populateDatabase();
		app.fetchInfoUsingEntitygraph();
	}
	
	private void fetchInfoUsingEntitygraph() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-samples");

		EntityManager em = emf.createEntityManager();
		
		
		em.getTransaction().begin();
		
		EntityGraph entityGraph = em.getEntityGraph("post-entity-graph-with-comments-and-users");
		Map<String, Object> properties = new HashMap<>();
		properties.put("javax.persistence.fetchgraph", entityGraph);
		
		Post post = em.find(Post.class,  Long.valueOf(1), properties);
		System.out.println("User '" + post.getUser().getName() + "' create a post with subject '" + post.getSubject() + "'");
		
		System.out.println("User '" + post.getComments().get(0).getUser().getName() + "' comments '" + post.getComments().get(0).getReply() + "'");
		
		System.out.println("User '" + post.getComments().get(1).getUser().getName() + "' comments '" + post.getComments().get(1).getReply() + "'");
		
		em.getTransaction().commit();
		
		emf.close();
	}

	private void populateDatabase() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-samples");

		EntityManager entityManager = emf.createEntityManager();

		try {
			entityManager.getTransaction().begin();

			User raam = new User();
			raam.setName("Raam");
			raam.setEmail("ram@swarg.com");

			User hanuman = new User();
			hanuman.setName("Hanuman");
			hanuman.setEmail("hanuman@swarg.com");

			User shiva = new User();
			shiva.setName("Shiva");
			shiva.setEmail("shiva@kailash.com");

			Post raamPost = new Post();
			raamPost.setSubject("Invasion");
			raamPost.setUser(raam);

			Comment hanumanComment = new Comment();
			hanumanComment.setReply("Jai Shri Raam");
			hanumanComment.setPost(raamPost);
			hanumanComment.setUser(hanuman);

			Comment shivaComment = new Comment();
			shivaComment.setReply("You have my blessing!!!");
			shivaComment.setPost(raamPost);
			shivaComment.setUser(shiva);

			raamPost.addComment(hanumanComment);
			raamPost.addComment(shivaComment);

			entityManager.persist(raam);
			entityManager.persist(hanuman);
			entityManager.persist(shiva);

			entityManager.persist(raamPost);

			entityManager.persist(hanumanComment);
			entityManager.persist(shivaComment);

			entityManager.getTransaction().commit();
		} finally {
			emf.close();
		}
	}
}
