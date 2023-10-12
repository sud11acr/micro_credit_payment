package com.project.micro.creditpayment.controller;

import com.project.micro.creditpayment.integration.CreditPaymentRequest;
import com.project.micro.creditpayment.integration.CreditPaymentResponse;
import com.project.micro.creditpayment.service.ICreditPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/creditpayment")
public class CreditPaymentController {

    @Autowired
    private ICreditPaymentService service;

    @GetMapping("/findById/{id}")
    public Mono<ResponseEntity<CreditPaymentResponse>> findById(@PathVariable String id){
        return service.findByid(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @GetMapping("/findAll")
    public Mono<ResponseEntity<Flux<CreditPaymentResponse>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.findAll()));
    }

    @PostMapping("/create/{id}")
    public Mono<ResponseEntity<CreditPaymentResponse>>save(@PathVariable String id,@Validated @RequestBody  Mono<CreditPaymentRequest> creditPaymentRequest){
        return service.save(id,creditPaymentRequest)
                .map(p -> ResponseEntity.created(URI.create("/create".concat("/").concat(p.getIdCreditPayment())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<CreditPaymentResponse>>update(@PathVariable String id,@RequestBody Mono<CreditPaymentRequest> creditPaymentRequest ){
        return service.update(id,creditPaymentRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.delete(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }
}
