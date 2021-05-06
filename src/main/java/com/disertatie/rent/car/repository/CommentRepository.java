package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.Car;
import com.disertatie.rent.car.entities.Comment;
import com.disertatie.rent.car.model.enumType.CommentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "commentRepository")
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByStatus(CommentType status);

    List<Comment> findAllByCarAndStatus(Car car, CommentType status);
}
