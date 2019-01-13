package com.example.springsocial.services;

import com.example.springsocial.pojos.PostsInput;

public interface PostsSaveService {

	PostsInput saveorCreatePost(PostsInput input);
}
