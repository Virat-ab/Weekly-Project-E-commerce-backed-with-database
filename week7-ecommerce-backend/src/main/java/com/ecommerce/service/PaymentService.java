package com.ecommerce.service;

import com.ecommerce.exception.PaymentFailedException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.entity.Order;
import com.ecommerce.model.entity.Payment;
import com.ecommerce.model.enums.OrderStatus;
import com.ecommerce.model.enums.PaymentStatus;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Payment processPayment(Long orderId, BigDecimal amount, String paymentMethod) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new PaymentFailedException("Cannot process payment for cancelled order");
        }

        // Simulate payment gateway call
        String transactionId = simulatePaymentGateway(amount, paymentMethod);

        Payment payment = Payment.builder()
                .order(order)
                .amount(amount)
                .paymentMethod(paymentMethod)
                .transactionId(transactionId)
                .status(PaymentStatus.COMPLETED)
                .paymentDate(LocalDateTime.now())
                .gatewayResponse("Payment processed successfully")
                .build();

        Payment saved = paymentRepository.save(payment);

        // Update order status
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);

        log.info("Payment {} processed for order {}", transactionId, orderId);
        return saved;
    }

    @Transactional
    public Payment refundPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", paymentId));

        if (payment.getStatus() != PaymentStatus.COMPLETED) {
            throw new PaymentFailedException("Can only refund completed payments");
        }

        payment.setStatus(PaymentStatus.REFUNDED);
        payment.setGatewayResponse("Payment refunded");

        Order order = payment.getOrder();
        order.setStatus(OrderStatus.REFUNDED);
        orderRepository.save(order);

        return paymentRepository.save(payment);
    }

    private String simulatePaymentGateway(BigDecimal amount, String method) {
        // In production, integrate with Stripe, PayPal, etc.
        return "TXN-" + UUID.randomUUID().toString().substring(0, 16).toUpperCase();
    }
}
