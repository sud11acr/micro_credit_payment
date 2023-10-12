package com.project.micro.creditpayment.service;

import com.project.micro.creditpayment.integration.CreditPaymentRequest;
import com.project.micro.creditpayment.integration.CreditPaymentResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditPaymentService {

    Mono<CreditPaymentResponse> findByid(String id);
    Flux<CreditPaymentResponse> findAll();
    Mono<CreditPaymentResponse>save(String id,Mono<CreditPaymentRequest> creditPaymentRequest);
    Mono<CreditPaymentResponse>update(String id,Mono<CreditPaymentRequest> creditPaymentRequest);
    Mono<Void>delete(String id);


}
