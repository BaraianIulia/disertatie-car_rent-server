package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value = "cardRepository")
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "SELECT * FROM CARDS c WHERE c.user_id = :userId", nativeQuery = true)
    List<Card> getAllCardsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM CARDS c WHERE c.user_id = :userId AND c.card_number = :cardNumber AND c.cvv_code = :cvvCode", nativeQuery = true)
    Optional<Card> findCardByUserAndCardNumberAndCvvCode(Long userId, String cardNumber, String cvvCode);
}
