package com.mega.gift.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mega.gift.model.Feedbacks;
import com.mega.gift.service.FeedbackService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
	@RequestMapping("/api")
	public class FeedbackController {

	    @Autowired
	    private FeedbackService feedbackService;

	    @GetMapping("/feedbacks")
	    public List<Feedbacks> getAllFeedbacks() {
	        return feedbackService.getAllFeedbacks();
	    }

	    @GetMapping("/feedbacks/{id}")
	    public ResponseEntity<Feedbacks> getFeedbackById(@PathVariable Long id) {
	        Optional<Feedbacks> feedbackOptional = feedbackService.getFeedbackById(id);
	        if (feedbackOptional.isPresent()) {
	            return ResponseEntity.ok(feedbackOptional.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @PostMapping("/feedbacks")
	    public ResponseEntity<String> addFeedback(@RequestBody Feedbacks feedbacks) {
	        Feedbacks savedFeedback = feedbackService.saveFeedback(feedbacks);
	        if (savedFeedback != null) {
	            return ResponseEntity.ok("Feedback added successfully!");
	        } else {
	            return ResponseEntity.badRequest().body("Failed to add feedback.");
	        }
	    }

	    @PutMapping("/feedbacks/{id}")
	    public ResponseEntity<String> updateFeedback(@PathVariable Long id, @RequestBody Feedbacks updatedFeedback) {
	        Optional<Feedbacks> feedbackOptional = feedbackService.getFeedbackById(id);
	        if (feedbackOptional.isPresent()) {
	            Feedbacks existingFeedback = feedbackOptional.get();
	            existingFeedback.setUser(updatedFeedback.getUser());
	            existingFeedback.setProductName(updatedFeedback.getProductName());
	            existingFeedback.setCategory(updatedFeedback.getCategory());
	            existingFeedback.setPhoneNumber(updatedFeedback.getPhoneNumber());
	            existingFeedback.setMessage(updatedFeedback.getMessage());

	            Feedbacks savedFeedback = feedbackService.saveFeedback(existingFeedback);
	            if (savedFeedback != null) {
	                return ResponseEntity.ok("Feedback updated successfully!");
	            } else {
	                return ResponseEntity.badRequest().body("Failed to update feedback.");
	            }
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/feedbacks/{id}")
	    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
	        boolean deleted = feedbackService.deleteFeedbackById(id);
	        if (deleted) {
	            return ResponseEntity.ok("Feedback deleted successfully!");
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	   
	}