package com.store.walmart.repository;

import com.store.walmart.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query(value = "SELECT * FROM items WHERE id IN (SELECT item_id FROM (SELECT item_id, COUNT(item_id) AS count FROM order_lines WHERE qty > 0 AND NOT item_id IN (SELECT c.item_id FROM order_lines c, Orders o WHERE c.order_id = o.id AND o.user_id = :userId) GROUP BY item_id ORDER BY count DESC LIMIT 3))",nativeQuery = true)
    List<Item> findItemByUserId(@Param("userId") long userId);
}
