package com.mega.gift.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.gift.model.PaymentDetails;

import com.mega.gift.repository.PaymentRepository;



@Service

public class GiftService {
	
	 @Autowired
	    private PaymentRepository paymentRepo;
	    

	 public PaymentDetails savePayment(PaymentDetails paymentDetails) {
	        // You can add more complex business logic here if needed before saving the payment details
	        return paymentRepo.save(paymentDetails);
	    }

	    public PaymentDetails findPaymentById(Long paymentId) {
	        Optional<PaymentDetails> paymentOptional = paymentRepo.findById(paymentId);
	        return paymentOptional.orElse(null);
	    }
	    
	    public List<PaymentDetails> getAllPaymentDetails() {
	        return paymentRepo.findAll();
	    }

	    public PaymentDetails savePaymentDetails(PaymentDetails paymentDetails) {
	        return paymentRepo.save(paymentDetails);
	    }
	    
	    
	    public boolean deletePaymentById(Long paymentId) {
	        if (paymentRepo.existsById(paymentId)) {
	            paymentRepo.deleteById(paymentId);
	            return true;
	        }
	        return false;
	    }


}
    