package com.disertatie.rent.car.service;

import com.disertatie.rent.car.entities.Comment;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.CommentModel;
import java.util.List;
public interface CommentService {
    void addComment(CommentModel commentModel);

    List<CommentModel> getPendingComments();

    List<CommentModel> getCommentsByCarId(Long carId);

    void deleteComment(Long commentId);

    void approveComment(Long commentId) throws ExceptionNotFound;
}
