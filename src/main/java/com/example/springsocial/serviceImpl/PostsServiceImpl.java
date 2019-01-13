package com.example.springsocial.serviceImpl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsocial.model.Posts;
import com.example.springsocial.model.PostsType;
import com.example.springsocial.pojos.PostsInput;
import com.example.springsocial.repository.PostsRepository;
import com.example.springsocial.security.TokenAuthenticationFilter;
import com.example.springsocial.services.PostsSaveService;
import com.example.springsocial.util.CommonUtils;

@Service
public class PostsServiceImpl implements PostsSaveService {
	private static final Logger logger = LoggerFactory.getLogger(PostsServiceImpl.class);

	@Autowired
	PostsRepository postsRepository;

	@Override
	public PostsInput saveorCreatePost(PostsInput input) {

		Posts ps = new Posts();
		Posts psFinal = new Posts();
		if (input.getHashedId().isEmpty() || input.getHashedId().trim().equalsIgnoreCase("")) {
			logger.info("Creating Hashed Id");
			ps.setHashedId(UUID.randomUUID().toString());
			ps.setData(input.getData());
			ps.setTitle(input.getTitle());
			ps.setType(PostsType.DRAFT);
			ps.setFeaturedImage(input.getFeaturedImage());

			psFinal = postsRepository.save(ps);
			input.setHashedId(psFinal.getHashedId());
		} else {
			psFinal = postsRepository.findByHashedId(input.getHashedId());

			psFinal.setHashedId(input.getHashedId());
			psFinal.setData(input.getData());
			psFinal.setTitle(input.getTitle());

			if (input.getType()!=null && input.getType().equals(PostsType.PUBLISHED)) {
				psFinal.setType(PostsType.PUBLISHED);
				psFinal.setUpdatedDate(CommonUtils.getCurrDateString());
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			psFinal.setFeaturedImage(input.getFeaturedImage());
			psFinal = postsRepository.save(psFinal);

			input.setHashedId(psFinal.getHashedId());
			input.setData(psFinal.getData());
			input.setTitle(psFinal.getTitle());

		}

		return input;
	}

}
