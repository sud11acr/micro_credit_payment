package com.project.micro.creditpayment.proxy.bean;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Generated
public class CreditBean {

    private String idCredit;
    private String idCustomer;
    private String creditType;
    private String descriptionType;
    private BigDecimal outstandingBalance;//Saldo Pendiente
    private BigDecimal creditAmount;//Monto del Crédito
}
