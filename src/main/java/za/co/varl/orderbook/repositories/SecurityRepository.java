package za.co.varl.orderbook.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.varl.orderbook.models.Security;

@Repository
public interface SecurityRepository extends CrudRepository<Security, Long> {
}
