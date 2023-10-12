package com.project.micro.creditpayment.proxy.service;

import com.project.micro.creditpayment.proxy.bean.CreditBean;
import reactor.core.publisher.Mono;

public interface ICreditPaymentProxy {

    Mono<CreditBean> findByIdCredit(String idCredit);
    Mono<CreditBean>updateCredit(String id,CreditBean creditBean);

}
