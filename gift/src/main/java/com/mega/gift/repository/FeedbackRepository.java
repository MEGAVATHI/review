package com.mega.gift.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mega.gift.model.Feedbacks;

@Repository

public interface FeedbackRepository extends JpaRepository<Feedbacks, Long> {
   
}