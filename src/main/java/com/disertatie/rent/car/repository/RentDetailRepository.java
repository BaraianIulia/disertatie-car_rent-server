package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.RentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository(value = "rentDetailRepository")
public interface RentDetailRepository extends JpaRepository<RentDetail, Long> {

    @Query(value = "SELECT * FROM RENT_DETAILS rd WHERE rd.car_id = :carId AND ((:endDate >= rd.start_date and :endDate <= rd.end_date) or (rd.start_date <= :startDate and rd.end_date >= :startDate) or (rd.start_date >= :startDate and rd.end_date <= :endDate))", nativeQuery = true)
    List<RentDetail> checkCarForAvailability(Long carId, LocalDate startDate, LocalDate endDate);
}

