package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.Car;
import com.disertatie.rent.car.entities.Comment;
import com.disertatie.rent.car.model.enumType.CommentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value = "commentRepository")
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByStatus(CommentType status);

    List<Comment> findAllByCarAndStatus(Car car, CommentType status);

    @Query(value = "SELECT * FROM COMMENTS c WHERE c.author_email = :authorEmail and c.car_id = :carId", nativeQuery = true)
    Optional<Comment> getCommentByAuthorEmailAndCarId(String authorEmail, Long carId);

   List<Comment> getAllByCarId(Long carId);

   List<Comment> getAllByAuthorEmail(String authorEmail);
}
