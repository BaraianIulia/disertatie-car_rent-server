package com.disertatie.rent.car.service.impl;

import com.disertatie.rent.car.entities.CarTotalRating;
import com.disertatie.rent.car.entities.Comment;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.CommentModel;
import com.disertatie.rent.car.model.enumType.CommentType;
import com.disertatie.rent.car.repository.CarRepository;
import com.disertatie.rent.car.repository.CarTotalRatingRepository;
import com.disertatie.rent.car.repository.CommentRepository;
import com.disertatie.rent.car.service.CommentService;
import com.disertatie.rent.car.transformers.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service(value = "commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    @Resource(name = "commentRepository")
    private CommentRepository commentRepository;

    @Resource(name = "carRepository")
    private CarRepository carRepository;

    @Resource(name = "carTotalRatingRepository")
    private CarTotalRatingRepository carTotalRatingRepository;

    @Resource(name = "transformer")
    private Transformer transformer;

    @Override
    public void addComment(CommentModel commentModel) {
        commentRepository.save(transformer.transformModelToEntity(commentModel));
    }

    @Override
    public List<CommentModel> getPendingComments() {
        List<Comment> commentList = commentRepository.findAllByStatus(CommentType.PENDING);
        List<CommentModel> commentModelList = new ArrayList<>();
        for (Comment comm : commentList
        ) {
            commentModelList.add(transformer.transformEntityToModel(comm));
        }
        return commentModelList;
    }

    @Override
    public List<CommentModel> getCommentsByCarId(Long carId) {
        List<Comment> commentList = commentRepository.findAllByCarAndStatus(carRepository.getOne(carId), CommentType.APPROVED);
        List<CommentModel> commentModelList = new ArrayList<>();
        for (Comment comm : commentList
        ) {
            commentModelList.add(transformer.transformEntityToModel(comm));
        }
        commentModelList = commentModelList.stream()
                .sorted(Comparator.comparingLong(CommentModel::getRating).reversed())
                .collect(Collectors.toList());
        return commentModelList;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void approveComment(Long commentId) throws ExceptionNotFound {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (!optionalComment.isPresent()) {
            throw new ExceptionNotFound("The comment with id: " + commentId + " was not found");
        } else {
            Comment comment = optionalComment.get();
            comment.setStatus(CommentType.APPROVED);
            commentRepository.save(comment);

            Optional<CarTotalRating> carTotalRatingOptional = carTotalRatingRepository.getByCarId(comment.getCar().getId());
            if (carTotalRatingOptional.isPresent()) {
                CarTotalRating carTotalRating = carTotalRatingOptional.get();
                Long rating = (carTotalRating.getRating() + comment.getRating()) / 2;
                carTotalRating.setRating(rating);
                carTotalRatingRepository.save(carTotalRating);
            } else {
                CarTotalRating carTotalRating = new CarTotalRating();
                carTotalRating.setRating(comment.getRating());
                carTotalRating.setCar(comment.getCar());
                carTotalRatingRepository.save(carTotalRating);
            }
        }
    }

    @Override
    public List<CommentModel> getAllByCarId(Long carId) {
        List<Comment> commentList = commentRepository.getAllByCarId(carId);
        List<CommentModel> commentModelList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentModelList.add(transformer.transformEntityToModel(comment));
        }
        return commentModelList;
    }

    @Override
    public CommentModel getUserCommentByCarId(String authorEmail, Long carId) {
        Optional<Comment> optionalComment = commentRepository.getCommentByAuthorEmailAndCarId(authorEmail, carId);
        if (optionalComment.isPresent()) {
            return transformer.transformEntityToModel(optionalComment.get());
        }
        return new CommentModel();

    }

    @Override
    public List<CommentModel> getAllUserCommentsByAuthorEmail(String authorEmail) {
        List<Comment> commentList = commentRepository.getAllByAuthorEmail(authorEmail);
        List<CommentModel> commentModelList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentModelList.add(transformer.transformEntityToModel(comment));
        }
        return commentModelList;
    }

}
