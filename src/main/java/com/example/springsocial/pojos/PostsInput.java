package com.example.springsocial.pojos;

import java.util.List;

import com.example.springsocial.model.PostsType;

public class PostsInput {

	String data;
	String textData;
	String title;
	String hashedId;
	String editor_content;
	String featuredImage;
	PostsType type;
	List<String> tags;
	boolean published;
	String postDate;


	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getTextData() {
		return textData;
	}

	public void setTextData(String textData) {
		this.textData = textData;
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

	
	public String getEditor_content() {
		return editor_content;
	}

	public void setEditor_content(String editor_content) {
		this.editor_content = editor_content;
	}

	public String getFeaturedImage() {
		return featuredImage;
	}
	

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@Override
	public String toString() {
		return "PostsInput [data=" + data + ", title=" + title + ", hashedId=" + hashedId + ", editor_content="
				+ editor_content + ", featuredImage=" + featuredImage + ", type=" + type + ", tags=" + tags + "]";
	}

	public void setFeaturedImage(String featuredImage) {
		this.featuredImage = featuredImage;
	}

	public PostsType getType() {
		return type;
	}

	public void setType(PostsType type) {
		this.type = type;
	}

}
