package com.example.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PayPalService {
	public Payment createPayment(Double total) throws PayPalRESTException;
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
