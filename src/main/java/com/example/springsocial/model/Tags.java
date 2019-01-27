package com.example.springsocial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Table(name = "tags")
public class Tags {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TAG_ID")
	private Long id;
	
	private String tagName;

	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TAGS_POSTS", joinColumns = { @JoinColumn(name = "TAG_ID") }, inverseJoinColumns = { @JoinColumn(name = "POST_ID") })
	private Set<Posts> post;
	 
	
	
	public Set<Posts> getPost() {
		return post;
	}

	public void setPost(Set<Posts> post) {
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
}
