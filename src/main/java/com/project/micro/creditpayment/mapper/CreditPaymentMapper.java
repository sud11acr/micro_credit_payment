package com.project.micro.creditpayment.mapper;

import com.project.micro.creditpayment.integration.CreditPaymentRequest;
import com.project.micro.creditpayment.integration.CreditPaymentResponse;
import com.project.micro.creditpayment.model.CreditPayment;
import org.springframework.beans.BeanUtils;

public class CreditPaymentMapper {

    public static CreditPaymentResponse toCreditPaymentModelResp(CreditPayment creditPayment){
        CreditPaymentResponse creditPaymentResponse=new CreditPaymentResponse();
        BeanUtils.copyProperties(creditPayment,creditPaymentResponse);
        return creditPaymentResponse;
    }

    public static CreditPayment toCreditPaymentReqModel(CreditPaymentRequest creditPaymentRequest){
        CreditPayment creditPayment=new CreditPayment();
        BeanUtils.copyProperties(creditPaymentRequest,creditPayment);
        return creditPayment;

    }
}
