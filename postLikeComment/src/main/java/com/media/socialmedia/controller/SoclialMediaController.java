package com.media.socialmedia.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.media.socialmedia.entity.Comment;
import com.media.socialmedia.entity.Like;
import com.media.socialmedia.entity.LikeId;
import com.media.socialmedia.entity.Post;
import com.media.socialmedia.entity.User;
import com.media.socialmedia.repository.LikeRepository;
import com.media.socialmedia.repository.PostRepository;
import com.media.socialmedia.repository.UserRepository;
import com.media.socialmedia.repository.CommentRepository;
import com.media.socialmedia.repository.LikeCountRepository;
import com.media.socialmedia.controller.exception.ResourceNotFoundException;


import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;

@Controller
public class SoclialMediaController {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private LikeCountRepository likeCountRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	//private List<User> userList = new ArrayList<>();
	
//	  public void getUsers() {
//	    userList = new ArrayList<>();
//	    userRepository.findAll().forEach(user -> userList.add(user));
//	    System.out.println(userList);
//	}

	
	@GetMapping("/UserForm")
	public String getUser(Model model) {
		//getUsers();
		List<User> userList = userRepository.findAll();

	    User user = new User(); 
	    model.addAttribute("user", user); 
	    model.addAttribute("userList", userList);
	    return "UserForm";
	}

	
	
	 @PostMapping("/user/add")
	    public String addUserSubmit(@ModelAttribute User user, Model model) {
	        // Check if the user already exists in the database
	        User existingUser = userRepository.findByUserName(user.getUserName());

	        if (existingUser == null) {
	            // User does not exist in the database, so add the user
	            userRepository.save(user);
	            model.addAttribute("successMessage", "User added successfully!"); // Add success message
	        } else {
	            // User already exists, print the ID and name to the console
	            System.out.println("User ID: " + existingUser.getUserId());
	            System.out.println("User Name: " + existingUser.getUserName());
	            model.addAttribute("successMessage", null); // No success message
	        }

	        return "UserForm"; // Return to the same form page
	    }
	 
	 @PostMapping("/user")
	    public String handlePostRequest(@RequestParam("userId") int selectedUserId, Model model) {

	        System.out.println("Selected User ID: " + selectedUserId);

	        return String.format("redirect:/User/%d/home", selectedUserId); 
	    }
	 
	 @GetMapping("/User/{id}/home")
	public String getHomePage(@PathVariable int id, Model model) throws ResourceNotFoundException {
		System.out.println("inside home page");
		Optional<User> u = userRepository.findById(id);
		if (u.isEmpty()) {
			 throw new ResourceNotFoundException("No user with the requested ID");
		}
		User  user = u.get(); 
		System.out.println(user.getUserName());
		model.addAttribute("user", user); 
		//List<Object[]> postData = postRepository.findPostTextAndAuthor();
		List<Post> postList = postRepository.findAll();
		List<Comment> commentList = commentRepository.findAll();

		

//		Iterable<Like> likeCounts = likeRepository.findAll();
//		Map<Integer, Integer> likeCountByPostId = new HashMap<>();
//		for (Like like : likeCounts) {
//	
//		    System.out.println("Like ID: " + like.getLikesId());
//		    System.out.println("Post ID: " + like.getLikeIdComposite().getPost().getPostId());
//		    System.out.println("User ID: " + like.getLikeIdComposite().getUser().getUserId());
//		    System.out.println("-----------------------------");
//		    int postId = like.getLikeIdComposite().getPost().getPostId();
//		    likeCountByPostId.put(postId, likeCountByPostId.getOrDefault(postId, 0) + 1);
//		}
//		for (Post post : postList) { 
//		    System.out.println("Post ID: " + post.getPostId());
//		    System.out.println("Post Text: " + post.getPostText());
//		    System.out.println("User Name: " + post.getUser().getUserName());
//		    System.out.println("-----------------------------");
//		    int post_Id = post.getPostId();
//		    Like like = new Like();
//            int likeCount = likeRepository.countByPostId(like.getLikeIdComposite().getPost().getPostId()); 
//            System.out.println(likeCount);
//            likeCounts.add(likeCount);
//		}
		    model.addAttribute("postList", postList);
		    model.addAttribute("commentList", commentList);
 
		    return "Home";
		}
	  
	 @PostMapping("/user/{userId}/home/addPost")
	 public String handlePostRequest(@PathVariable int userId, @RequestParam String message,  Model model) throws ResourceNotFoundException {

	     System.out.println("User ID: " + userId);
	     System.out.println("Message Text: " + message);
	     if(message != null) {
	    	 Post post = new Post();
	    	 post.setPostText(message);
	    	 User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	    	  post.setUser(user);
	    	 
	    	  postRepository.save(post);
	     }   
	     return "redirect:/User/" + userId + "/home"; 
	 }
	 
	 @PostMapping("/user/{userId}/post/{postId}/like")
	    public String likePost(@PathVariable int userId, @PathVariable int postId,  Model model) throws ResourceNotFoundException {
	        System.out.println("Inside like");
	        System.out.println("User ID: " + userId);
	        System.out.println("Post ID: " + postId);
	        
	        Like like = new Like();
	        LikeId likeId = new LikeId();
	        Optional<User> user = userRepository.findById(userId);
	        Optional<Post> post = postRepository.findById(postId);
	        if(user.isPresent() && post.isPresent()) {
	        	likeId.setUser(user.get());
	        	likeId.setPost(post.get());
	        	like.setLikeIdComposite(likeId);
	        	likeRepository.save(like);
	        	System.out.println("like added to db");
	        }

	        return "redirect:/User/" + userId + "/home";  
	    }
	 
	 
	 @PostMapping("/user/{userId}/post/{postId}/comment")
	    public String submitComment(@PathVariable int userId, @PathVariable int postId, @RequestParam String comment) throws ResourceNotFoundException  {

	        System.out.println("User ID: " + userId);
	        System.out.println("Post ID: " + postId);
	        System.out.println("Comment: " + comment);
	        
	        Comment newComment = new Comment();
	        newComment.setCommentText(comment);

	        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	        newComment.setUser(user);

	        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
	        newComment.setPost(post);

	        
	        commentRepository.save(newComment);

	        return "redirect:/User/" + userId + "/home";
	    }

	
}