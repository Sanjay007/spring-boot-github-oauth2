package com.example.springsocial.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.pojos.ApiResponse;
import com.example.springsocial.repository.TagRepository;
import com.example.springsocial.services.PostsSaveService;

@RestController
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PostsController {
	
@Autowired
PostsSaveService postsaveService;
@Autowired
TagRepository tagRepository;

    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public @ResponseBody ApiResponse getAllPosts()
			throws IOException {
    	
		
		return new ApiResponse(postsaveService.getAllDashBoard());
	}
    
    @CrossOrigin(origins = "http://localhost:3000")
  	@RequestMapping(value = "/tags", method = RequestMethod.GET)
  	public @ResponseBody ApiResponse getAlltags()
  			throws IOException {
      	
  		
  		return new ApiResponse(tagRepository.findAll());
  	}
    
}
