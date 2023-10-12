package com.project.micro.creditpayment.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditPaymentDto {

    private String idCreditPayment;
    private String idCredit;
    private BigDecimal paymentAmount;
    private Date paymentDate;

}
