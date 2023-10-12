package com.project.micro.creditpayment.utils;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ExternalProperties {
    @Value("${url.credit}")
    public String urlCredit;
}
