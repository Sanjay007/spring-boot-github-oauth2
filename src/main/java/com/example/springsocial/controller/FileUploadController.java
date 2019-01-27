package com.example.springsocial.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springsocial.pojos.ApiResponse;
import com.example.springsocial.pojos.FileData;
import com.example.springsocial.pojos.PostsInput;
import com.example.springsocial.security.TokenAuthenticationFilter;
import com.example.springsocial.services.PostsSaveService;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;


@RestController
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	PostsSaveService postsSaveService;
	
	String UPLOAD_DIR = "D://upload/";
	String out ="";
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody ApiResponse handleFileUpload(@RequestParam(value = "file") MultipartFile file)
			throws IOException {

		String fileExtension = getFileExtension(file);
		String filename = getRandomString();

		File targetFile = getTargetFile(fileExtension, filename);

		byte[] bytes = file.getBytes();
		file.transferTo(targetFile);
		String UploadedDirectory = targetFile.getAbsolutePath();
		FileData dt = new FileData();
		dt.setLink("http://localhost:9000/upload/" + filename);
		return new ApiResponse(dt);
	}

    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/upload/{galleryId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFile(@PathVariable("galleryId") String galleryId) throws IOException {

		byte[] bFile = Files.readAllBytes(new File(UPLOAD_DIR + galleryId + ".jpg").toPath());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		headers.setContentLength(bFile.length);

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bFile, headers, HttpStatus.OK);
		return responseEntity;

	}
	
    @CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/posts")
	public @ResponseBody ApiResponse addPosts(@RequestBody PostsInput data) throws ParseException {
    	System.out.println(data.toString());
		String datast=(data.getData().toString());
		String ju4="";
try{
	JSONObject jo = new JSONObject(datast);
	JSONObject ju=(JSONObject) jo.get("entityMap");
	JSONObject ju2=(JSONObject) ju.get("0");
	//System.out.println(ju2.toString());
	JSONObject ju3=(JSONObject) ju2.get("data");
	//System.out.println(ju3.toString());
	 ju4=(String) ju3.get("src");
	System.out.println(ju4.toString());
	
}catch (Exception e) {
	
}


        data.setFeaturedImage(ju4);
		out=data.getData();
    	PostsInput inp=postsSaveService.saveorCreatePost(data);
		return new ApiResponse(inp);
	}
    
    @CrossOrigin(origins = "http://localhost:3000")
  	@PostMapping("/posts_new")
  	public @ResponseBody ApiResponse addnew(@RequestBody PostsInput data) {
  		System.out.println(data.getEditor_content());
      	out=data.getData();
      	//PostsInput inp=postsSaveService.saveorCreatePost(data);
  		return new ApiResponse("out");
  	}
    
	
    @CrossOrigin(origins = "http://localhost:3000")
   	@GetMapping("/posts")
   	public @ResponseBody ApiResponse getPosts() {
   		
       
   		return new ApiResponse(out);
   	}
    

	private String getRandomString() {
		return new Random().nextInt(999999) + "_" + System.currentTimeMillis();
	}

	private File getTargetFile(String fileExtn, String fileName) {
		File targetFile = new File(UPLOAD_DIR + fileName + fileExtn);
		return targetFile;
	}

	private String getFileExtension(MultipartFile inFile) {
		String fileExtention = inFile.getOriginalFilename().substring(inFile.getOriginalFilename().lastIndexOf('.'));
		return fileExtention;
	}

}
