package com.example.springsocial.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsocial.model.Tags;

public interface TagRepository extends JpaRepository<Tags, Long> {

}
