package com.mega.gift.controller;


import java.util.List;


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
import org.springframework.web.bind.annotation.RestController;

import com.mega.gift.model.PaymentDetails;

import com.mega.gift.service.GiftService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private GiftService giftService;



    @PostMapping("/payment")
    public ResponseEntity<String> processPayment(@RequestBody PaymentDetails paymentDetails) {
        // Handle payment processing here, e.g., saving payment details to the database
        // You can access the payment details from the "paymentDetails" object

        // Save the payment details using the service
        PaymentDetails savedPayment = giftService.savePayment(paymentDetails);

        if (savedPayment != null) {
            return ResponseEntity.ok("Payment processed successfully!");
        } else {
            return ResponseEntity.badRequest().body("Failed to process payment.");
        }
    }
     
    @GetMapping("/getdetails")
    public List<PaymentDetails> getAllPaymentDetails() {
        return giftService.getAllPaymentDetails();
    }
    
    
    @DeleteMapping("/payment/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        boolean deleted = giftService.deletePaymentById(id);
        if (deleted) {
            return ResponseEntity.ok("Payment deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<String> updatePayment(@PathVariable Long id, @RequestBody PaymentDetails updatedPaymentDetails) {
        PaymentDetails existingPayment = giftService.findPaymentById(id);
        if (existingPayment != null) {
            // Update the fields of existingPayment with the fields from updatedPaymentDetails
            existingPayment.setImageId(updatedPaymentDetails.getImageId());
            existingPayment.setName(updatedPaymentDetails.getName());
            existingPayment.setProduct(updatedPaymentDetails.getProduct());
            existingPayment.setColor(updatedPaymentDetails.getColor());

            PaymentDetails savedPayment = giftService.savePayment(existingPayment);
            if (savedPayment != null) {
                return ResponseEntity.ok("Payment updated successfully!");
            } else {
                return ResponseEntity.badRequest().body("Failed to update payment.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

}
