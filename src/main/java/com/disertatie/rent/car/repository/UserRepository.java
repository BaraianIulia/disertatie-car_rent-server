package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


}


