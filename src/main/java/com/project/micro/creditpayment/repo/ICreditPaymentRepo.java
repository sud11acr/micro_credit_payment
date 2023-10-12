package com.project.micro.creditpayment.repo;

import com.project.micro.creditpayment.model.CreditPayment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ICreditPaymentRepo extends ReactiveMongoRepository<CreditPayment,String> {
}
