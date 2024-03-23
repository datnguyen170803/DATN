package com.example.controller.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.helper.PageTypeEnum;
import com.example.model.Order;
import com.example.model.User;
import com.example.service.OrderService;
import com.example.service.PayPalService;
import com.example.service.SessionService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@RequestMapping(value = "shop/order-history")
public class OrderHistoryController {

	@Autowired
	SessionService session;

	@Autowired
	OrderService orderService;

	@Autowired
	PayPalService paypal;

	@GetMapping(value = "")
	public String orderHistoryPage(Model model) {
		User user = session.get("shop");
		List<Order> list = orderService.findAllByEmail(user.getEmail());
		model.addAttribute("list", list);

		return PageTypeEnum.SHOP_ORDER_HISTORY.type;
	}

	@GetMapping(value = "/complete")
	public String completeOrder(Model model, @RequestParam("paymentId") String paymentId,
			@RequestParam("PayerID") String payerId) {
		User user = session.get("shop");
		List<Order> list = orderService.findAllByEmail(user.getEmail());
		model.addAttribute("list", list);

		try {
			if (!paymentId.equals("") && !payerId.equals("")) {
				Payment payment = paypal.executePayment(paymentId, payerId);
				if (payment.getState().equals("approved")) {
					// Xử lý khi thanh toán được chấp nhận
				}
				System.out.println(payerId);
				System.out.println(paymentId);
			}
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		}

		return PageTypeEnum.SHOP_ORDER_HISTORY.type;
	}
}
