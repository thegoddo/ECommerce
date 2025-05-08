package lnct.project.ECommerce.services;

import lnct.project.ECommerce.payload.PaymentDetails;

public interface PaymentService {
    public PaymentDetails CreateOrder(Double amount);
}
