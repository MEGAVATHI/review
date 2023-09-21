package com.mega.gift.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mega.gift.model.PaymentDetails;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {

}
