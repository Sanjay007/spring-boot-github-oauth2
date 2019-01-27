package com.example.springsocial.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsocial.model.Posts;
import com.example.springsocial.model.PostsType;
import com.example.springsocial.model.User;
import com.example.springsocial.output.PostsOut;
import com.example.springsocial.pojos.PostsInput;
import com.example.springsocial.repository.PostsRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.TokenAuthenticationFilter;
import com.example.springsocial.services.PostsSaveService;
import com.example.springsocial.util.CommonUtils;
import com.nimbusds.jose.proc.SecurityContext;

@Service
public class PostsServiceImpl implements PostsSaveService {
	private static final Logger logger = LoggerFactory.getLogger(PostsServiceImpl.class);

	@Autowired
	PostsRepository postsRepository;
	@Autowired
	UserRepository  userRepository;
	@Override
	public PostsInput saveorCreatePost(PostsInput input) {

		Posts ps = new Posts();
		Posts psFinal = new Posts();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userRepository.findByEmail(currentPrincipalName)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + currentPrincipalName));
		
		logger.info("User Id"+currentPrincipalName);
//
//		int words=countWords(input.getTextData());
//		 int wpm = 200;
//		    int estimatedTime =words / wpm;
//		    int minutes = Math.round(estimatedTime);
//		    String finaTime = minutes < 1 ? "a couple of secs" : minutes + " min read";
//		System.out.println("*********&&&&&&&&&"+finaTime);
//	    
		if (input.getHashedId().isEmpty() || input.getHashedId().trim().equalsIgnoreCase("")) {
			logger.info("Creating Hashed Id");
			ps.setHashedId(UUID.randomUUID().toString());
			ps.setData(input.getData());
			ps.setTitle(input.getTitle());
			if(input.getTextData()!=null){
				ps.setTextData(input.getTextData());
			}
			ps.setType(PostsType.DRAFT);
			ps.setFeaturedImage(input.getFeaturedImage());
			ps.setUpdatedDate(CommonUtils.getCurrDateString(new Date()));
			ps.setUser(user);
			psFinal = postsRepository.save(ps);
			input.setHashedId(psFinal.getHashedId());
			input.setPostDate(psFinal.getUpdatedDate());
		} else {
			psFinal = postsRepository.findByHashedId(input.getHashedId());
			psFinal.setUser(user);

			psFinal.setHashedId(input.getHashedId());
			psFinal.setData(input.getData());
			psFinal.setTitle(input.getTitle());
			if(input.getTextData()!=null){
				psFinal.setTextData(input.getTextData());
			}
			if (input.getType()!=null && input.getType().equals(PostsType.PUBLISHED)) {
				psFinal.setType(PostsType.PUBLISHED);
				psFinal.setUpdatedDate(CommonUtils.getCurrDateString(new Date()));
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
			input.setPostDate((psFinal.getUpdatedDate()));

		}

		return input;
	}
	
	static int countWords(String str) 
    { final int OUT = 0; 
    final int IN = 1;
        int state = OUT; 
        int wc = 0;  // word count 
        int i = 0; 
          
        // Scan all characters one by one 
        while (i < str.length()) 
        { 
            // If next character is a separator, set the  
            // state as OUT 
            if (str.charAt(i) == ' ' || str.charAt(i) == '\n' 
                    || str.charAt(i) == '\t') 
                state = OUT; 
                  
       
            // If next character is not a word separator 
            // and state is OUT, then set the state as IN 
            // and increment word count 
            else if (state == OUT) 
            { 
                state = IN; 
                ++wc; 
            } 
       
            // Move to next character 
            ++i; 
        } 
        return wc; 
    }

	@Override
	public List<PostsOut> getAllDashBoard() {
		
		List<Posts> ps=postsRepository.findByType(PostsType.PUBLISHED);
		System.out.println("dddddddddddd"+ps.size());
		List<PostsOut> out=new ArrayList<PostsOut>();
		
		for(int i=0;i<ps.size();i++){
			Posts pos=ps.get(i);
			
			PostsOut op=new PostsOut();
			op.setAvatarSrc(pos.getUser().getImageUrl());
			op.setImageSrc(pos.getFeaturedImage());
			op.setPostDate(pos.getUpdatedDate());
			op.setShortDescription(pos.getTextData());
			op.setTitle(pos.getTitle());
			out.add(op);
			
		}
		
		
		return out;
	} 
	
	

}
