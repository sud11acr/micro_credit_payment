package com.project.micro.creditpayment.service.impl;

import com.project.micro.creditpayment.exception.ErrorException;
import com.project.micro.creditpayment.integration.CreditPaymentRequest;
import com.project.micro.creditpayment.integration.CreditPaymentResponse;
import com.project.micro.creditpayment.mapper.CreditPaymentMapper;
import com.project.micro.creditpayment.model.CreditPayment;
import com.project.micro.creditpayment.proxy.bean.CreditBean;
import com.project.micro.creditpayment.proxy.service.ICreditPaymentProxy;
import com.project.micro.creditpayment.repo.ICreditPaymentRepo;
import com.project.micro.creditpayment.service.ICreditPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class CreditPaymentServiceImpl implements ICreditPaymentService {

    @Autowired
    private ICreditPaymentRepo repo;

    @Autowired
    private ICreditPaymentProxy proxy;
    @Override
    public Mono<CreditPaymentResponse> findByid(String id) {
        return repo.findById(id).map(CreditPaymentMapper::toCreditPaymentModelResp);
    }

    @Override
    public Flux<CreditPaymentResponse> findAll() {
        return repo.findAll().map(CreditPaymentMapper::toCreditPaymentModelResp);
    }

    @Override
    public Mono<CreditPaymentResponse> save(String idCredit,Mono<CreditPaymentRequest> creditPaymentRequest) {

        return proxy.findByIdCredit(idCredit).
                flatMap(credit->{
                    return creditPaymentRequest
                            .map(CreditPaymentMapper::toCreditPaymentReqModel)
                            .flatMap(p->{
                                p.setRegistrationDate(new Date());
                                p.setModificationDate(new Date());
                                p.setStatus(true);
                                BigDecimal newBalance=credit.getOutstandingBalance().subtract(p.getPaymentAmount());

                                if((newBalance.compareTo(BigDecimal.ZERO))<0){

                                    return Mono.error(new ErrorException("El valor de pago es mayor al saldo pendiente"));
                                }
                                creditUpdate(newBalance,p.getIdCredit(),credit);
                                return repo.save(p);
                            })
                            .map(CreditPaymentMapper::toCreditPaymentModelResp);
                });
        /*

        return creditPaymentRequest.map(CreditPaymentMapper::toCreditPaymentReqModel)
                .flatMap(p->{
                    p.setRegistrationDate(new Date());
                    p.setModificationDate(new Date());
                    p.setStatus(true);
                    return repo.save(p);
                })
                .map(CreditPaymentMapper::toCreditPaymentModelResp);

         */
    }

    @Override
    public Mono<CreditPaymentResponse> update(String id, Mono<CreditPaymentRequest> creditPaymentRequest) {
        Mono<CreditPayment> monoBody = creditPaymentRequest.map(p-> CreditPaymentMapper.toCreditPaymentReqModel(p));
        Mono<CreditPayment> monoBD = repo.findById(id);

        return monoBD.zipWith(monoBody,(bd,pl)->{
                    bd.setModificationDate(new Date());
                    bd.setIdCredit(pl.getIdCredit());
                    bd.setPaymentAmount(pl.getPaymentAmount());
                    bd.setPaymentDate(pl.getPaymentDate());
                    return bd;
                }).flatMap(p->repo.save(p))
                .map(c->CreditPaymentMapper.toCreditPaymentModelResp(c));

    }

    @Override
    public Mono<Void> delete(String id) {
        return repo.deleteById(id);
    }

    private Mono<Boolean>creditUpdate(BigDecimal balance, String idCredit, CreditBean creditBean) {
        creditBean.setOutstandingBalance(balance);
        proxy.updateCredit(idCredit,creditBean).subscribe();
        return Mono.just(true);
    }
}
