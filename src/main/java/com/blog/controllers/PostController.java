package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.config.Appconstants;
import com.blog.payloads.Apiresponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
     @PostMapping("/user/{userId}/category/{categoryId}/posts")
     public ResponseEntity<PostDto> createPost(
    		 @RequestBody PostDto postDto,
    		 @PathVariable Integer userId,
    		 @PathVariable Integer categoryId
    		 ){
    	 
    	 PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
    	 return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
     }
     
     
     @GetMapping("/user/{userId}/posts")
     public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
    	 List<PostDto> posts=this.postService.getPostsByUser(userId);
    	 return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
     }
     @GetMapping("/category/{categoryId}/posts")
     public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
    	 List<PostDto> posts=this.postService.getPostsByCategory(categoryId);
    	 return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
     }
     @GetMapping("/posts")
     public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = Appconstants.PAGE_NUMBER,required = false) Integer pageNumber,
    		 @RequestParam(value = "pageSize",defaultValue = Appconstants.PAGE_SIZE,required = false) Integer pageSize,
    		 @RequestParam(value="sortBy",defaultValue = Appconstants.SORT_BY,required = false)String sortBy){
PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy);
    	 return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
     }
     @GetMapping("/post/{postId}")
     public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
    	 PostDto postDto=this.postService.getPostById(postId);
    	 return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);     }
 

@DeleteMapping("post/{postId}")
public Apiresponse deletePost(@PathVariable Integer postId) {
	this.postService.deletepost(postId);
	return new Apiresponse("Post is Deleted Successfully",true);
}

@PutMapping("/post/{postId}")
public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
	PostDto updatePost=this.postService.updatePost(postDto, postId);
	return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
}

@GetMapping("/posts/search/{keywords}")
public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
	List<PostDto> result=this.postService.searchPosts(keywords);
	return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
}
}