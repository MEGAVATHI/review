package com.mega.gift.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.gift.model.Feedbacks;
import com.mega.gift.repository.FeedbackRepository;

@Service

public class FeedbackService {

	@Autowired
    private FeedbackRepository feedbacksRepository;

    public List<Feedbacks> getAllFeedbacks() {
        return feedbacksRepository.findAll();
    }

    public Optional<Feedbacks> getFeedbackById(Long id) {
        return feedbacksRepository.findById(id);
    }

    public Feedbacks saveFeedback(Feedbacks feedback) {
        return feedbacksRepository.save(feedback);
    }

    public boolean deleteFeedbackById(Long id) {
        Optional<Feedbacks> feedbackOptional = feedbacksRepository.findById(id);
        if (feedbackOptional.isPresent()) {
            feedbacksRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
