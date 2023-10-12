package com.project.micro.creditpayment.proxy.service.impl;

import com.project.micro.creditpayment.proxy.bean.CreditBean;
import com.project.micro.creditpayment.proxy.service.ICreditPaymentProxy;
import com.project.micro.creditpayment.utils.ExternalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class CreditPaymentProxyImpl implements ICreditPaymentProxy {

    @Autowired
    private ExternalProperties p;

    @Override
    public Mono<CreditBean> findByIdCredit(String idCredit) {
        WebClient webClient= WebClient.builder().baseUrl(p.urlCredit).build();
        return webClient.get()
                .uri("/findById/{id}", Collections.singletonMap("id", idCredit))
                .retrieve()
                .bodyToMono(CreditBean.class);

    }

    @Override
    public Mono<CreditBean> updateCredit(String id, CreditBean creditBean) {
        WebClient webClient= WebClient.builder().baseUrl(p.urlCredit).build();
        return webClient.put()
                .uri("/update/{id}", Collections.singletonMap("id", id))
                .body(BodyInserters.fromValue(creditBean))
                .retrieve()
                .bodyToMono(CreditBean.class);
    }

}
