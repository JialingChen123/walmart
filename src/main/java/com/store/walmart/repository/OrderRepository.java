package com.store.walmart.repository;


import com.store.walmart.entity.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query(value = "SELECT COUNT(item_id) FROM order_lines WHERE order_id = :orderId UNION ALL SELECT COUNT(id) FROM orders WHERE id = :orderId", nativeQuery = true)
    List<Integer> findByOrderId(@Param("orderId") long orderId);

}
