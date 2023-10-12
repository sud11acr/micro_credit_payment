package com.project.micro.creditpayment.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("creditPayment")
public class CreditPayment {

    @Id
    private String idCreditPayment;
    private String idCredit;
    private BigDecimal paymentAmount;
    private Date paymentDate;
    private Date registrationDate;
    private Date modificationDate;
    private Boolean status;


}
