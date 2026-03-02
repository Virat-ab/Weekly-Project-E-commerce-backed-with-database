package com.ecommerce.repository;

import com.ecommerce.model.entity.Payment;
import com.ecommerce.model.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTransactionId(String transactionId);

    List<Payment> findByOrderId(Long orderId);

    List<Payment> findByStatus(PaymentStatus status);

    @Query("SELECT p FROM Payment p WHERE p.order.id = :orderId AND p.status = 'COMPLETED'")
    Optional<Payment> findCompletedPaymentByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.order.id = :orderId AND p.status = 'COMPLETED'")
    java.math.BigDecimal sumCompletedPaymentsByOrderId(@Param("orderId") Long orderId);
}
