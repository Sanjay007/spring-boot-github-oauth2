package com.example.springsocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.Posts;
import com.example.springsocial.model.PostsType;
import com.example.springsocial.model.User;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

	Posts findByHashedId(String hashedId);
	
	List<Posts> findByType(PostsType published);
	
}
