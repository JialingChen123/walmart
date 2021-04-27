package com.store.walmart.repository;

import java.util.Optional;

import com.store.walmart.entity.OrderLines;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLinesRepository extends JpaRepository<OrderLines, Long> {

    @Query("SELECT c FROM order_lines c where c.orderId = :orderId AND c.itemId = :itemId")
    Optional<OrderLines> findByOrderIdAndItemId(@Param("orderId") long orderId, @Param("itemId") long itemId);
}