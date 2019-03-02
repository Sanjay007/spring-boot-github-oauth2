package com.example.springsocial.output;

import java.util.List;

public class DashBoardOutput {

	List<PostsOut> posts;
	List<PostsOut> featured;
	List<PostsOut> editorPicks;
	List<PostsOut> suggestedRead;
	
	public List<PostsOut> getPosts() {
		return posts;
	}
	public void setPosts(List<PostsOut> posts) {
		this.posts = posts;
	}
	public List<PostsOut> getFeatured() {
		return featured;
	}
	public void setFeatured(List<PostsOut> featured) {
		this.featured = featured;
	}
	public List<PostsOut> getEditorPicks() {
		return editorPicks;
	}
	public void setEditorPicks(List<PostsOut> editorPicks) {
		this.editorPicks = editorPicks;
	}
	public List<PostsOut> getSuggestedRead() {
		return suggestedRead;
	}
	public void setSuggestedRead(List<PostsOut> suggestedRead) {
		this.suggestedRead = suggestedRead;
	}
	
	
}
