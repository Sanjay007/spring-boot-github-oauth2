package com.example.springsocial.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "posts", uniqueConstraints = { @UniqueConstraint(columnNames = "hashedId") })
public class Posts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="POST_ID")
	private Long id;

	private String title;

	private String hashedId;

	@Column(name="data", columnDefinition="TEXT")
	private String data;

	private String updatedDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	private PostsType type;

	private String featuredImage;
	
	@Column(name="textData", columnDefinition="TEXT")
	private String textData;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    
	@OneToMany(mappedBy="post")
    private Set<Tags> tags;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public String getTextData() {
		return textData;
	}

	public void setTextData(String textData) {
		this.textData = textData;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHashedId() {
		return hashedId;
	}

	public void setHashedId(String hashedId) {
		this.hashedId = hashedId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public PostsType getType() {
		return type;
	}

	public void setType(PostsType type) {
		this.type = type;
	}

	public String getFeaturedImage() {
		return featuredImage;
	}

	public void setFeaturedImage(String featuredImage) {
		this.featuredImage = featuredImage;
	}

}
