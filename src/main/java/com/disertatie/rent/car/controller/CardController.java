package com.disertatie.rent.car.controller;


import com.disertatie.rent.car.exceptions.ExceptionExistingCard;
import com.disertatie.rent.car.model.CardModel;
import com.disertatie.rent.car.service.CardService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/cards")
public class CardController {

    private final static Logger LOGGER = Logger.getLogger(CardController.class.getName());

    @Resource(name = "cardService")
    private CardService cardService;

    @GetMapping(path = "/list/{userId}", produces = "application/json")
    public ResponseEntity<List<CardModel>> getAllCardsOfUser(@PathVariable(name = "userId") Long userId) {
        LOGGER.info("CardController : getAllCardsOfUser()" + userId);
        return ResponseEntity.ok().body(cardService.getAllCardsOfUser(userId));
    }

    @DeleteMapping(path = "/delete/{cardId}", produces = "application/json")
    public ResponseEntity deleteCard(@PathVariable(name = "cardId") Long cardId) {
        LOGGER.info("CardController : deleteCard()" + cardId);
        cardService.deleteCard(cardId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<CardModel> addCard(@RequestBody CardModel cardModel) throws ExceptionExistingCard {
        LOGGER.info("CardController : addCard()" + cardModel);
        return ResponseEntity.ok(cardService.addCard(cardModel));
    }
}
