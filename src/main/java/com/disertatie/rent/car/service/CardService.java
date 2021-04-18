package com.disertatie.rent.car.service;

import com.disertatie.rent.car.exceptions.ExceptionExistingCard;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.CardModel;

import java.util.List;

public interface CardService {
    List<CardModel> getAllCardsOfUser(Long userId);

    void deleteCard(Long cardId);

    CardModel addCard(CardModel cardModel) throws ExceptionExistingCard;

    void checkCard(String cardNumber, String cvvCode, Long userId) throws ExceptionNotFound;
}
