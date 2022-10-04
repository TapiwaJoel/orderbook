package za.co.varl.orderbook.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.varl.orderbook.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
