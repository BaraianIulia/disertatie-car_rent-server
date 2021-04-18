package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "receiptRepository")
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
