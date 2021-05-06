package com.disertatie.rent.car.controller;

import com.disertatie.rent.car.entities.Comment;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.CommentModel;
import com.disertatie.rent.car.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    private final static Logger LOGGER = Logger.getLogger(CommentController.class.getName());

    @Resource(name = "commentService")
    private CommentService commentService;

    @PostMapping(path = "/add")
    public ResponseEntity addComment(@RequestBody CommentModel commentModel) {
        LOGGER.info("CommentController : add ");
        commentService.addComment(commentModel);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/pending")
    public ResponseEntity<List<CommentModel>> getPendingComments() {
        LOGGER.info("CommentController : getPendingComments ");
        return ResponseEntity.ok().body(commentService.getPendingComments());
    }

    @GetMapping(path = "/list/{carId}")
    public ResponseEntity<List<CommentModel>> getCommentsByCarId(@PathVariable(name = "carId") Long carId) {
        LOGGER.info("CommentController : getCommentsByCarId "+carId);
        return ResponseEntity.ok().body(commentService.getCommentsByCarId(carId));
    }

    @DeleteMapping(path = "/delete/{commentId}", produces = "application/json")
    public ResponseEntity deleteComment(@PathVariable(name = "commentId") Long commentId) {
        LOGGER.info("CommentController : deleteComment()" + commentId);
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/approve/{commentId}")
    public ResponseEntity approveComment(@PathVariable(name = "commentId") Long commentId) throws ExceptionNotFound {
        LOGGER.info("CommentController : approveComment()" + commentId);
        commentService.approveComment(commentId);
        return ResponseEntity.ok().build();
    }

}