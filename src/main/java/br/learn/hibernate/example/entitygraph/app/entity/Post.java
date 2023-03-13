package br.learn.hibernate.example.entitygraph.app.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "POST")
/*
 * @NamedEntityGraph(name = "post-entity-graph", attributeNodes = {
 * 
 * @NamedAttributeNode("subject"),
 * 
 * @NamedAttributeNode("user"),
 * 
 * @NamedAttributeNode("comments") })
 */

@NamedEntityGraph(name = "post-entity-graph-with-comments-and-users", attributeNodes = {

		@NamedAttributeNode("subject"),

		@NamedAttributeNode("user"),

		@NamedAttributeNode(value = "comments", subgraph = "comments-subgraph") }, subgraphs = {

				@NamedSubgraph(name = "comments-subgraph", attributeNodes = {

						@NamedAttributeNode("user") }) })

/*
 * @NamedEntityGraph(name = "post-entity-graph-with-comments-and-users",
 * attributeNodes = {
 * 
 * @NamedAttributeNode("subject"),
 * 
 * @NamedAttributeNode("user"),
 * 
 * @NamedAttributeNode("comments") } )
 */
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subject;
	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<Comment>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Comment> getComments() {
		return Collections.unmodifiableList(this.comments);
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
