package com.example.springsocial.services;

import java.util.List;

import com.example.springsocial.output.PostsOut;
import com.example.springsocial.pojos.PostsInput;

public interface PostsSaveService {

	PostsInput saveorCreatePost(PostsInput input);
	
	List<PostsOut> getAllDashBoard();
}
