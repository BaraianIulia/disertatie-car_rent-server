package com.disertatie.rent.car.service.impl;

import com.disertatie.rent.car.entities.Card;
import com.disertatie.rent.car.exceptions.ExceptionExistingCard;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.CardModel;
import com.disertatie.rent.car.repository.CardRepository;
import com.disertatie.rent.car.service.CardService;
import com.disertatie.rent.car.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(value = "cardService")
@Transactional
public class CardServiceImp implements CardService {

    @Resource(name = "cardRepository")
    private CardRepository cardRepository;

    @Resource(name = "transformer")
    private Transformer transformer;

    @Override
    public List<CardModel> getAllCardsOfUser(Long userId) {
        List<CardModel> cardModels = new ArrayList<>();
        List<Card> cardList = cardRepository.getAllCardsByUserId(userId);
        for (Card card : cardList
        ) {
            cardModels.add(transformer.transformEntityToModel(card));
        }
        return cardModels;
    }

    @Override
    public void deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }

    @Override
    public CardModel addCard(CardModel cardModel) throws ExceptionExistingCard {
        List<Card> cardList = cardRepository.getAllCardsByUserId(cardModel.getUserId());
        if(cardList.stream().anyMatch(card -> card.getCardNumber().equals(cardModel.getCardNumber()))){
            throw new ExceptionExistingCard("You already have the card with the number: " + cardModel.getCardNumber());
        }else {
            return transformer.transformEntityToModel(cardRepository.save(transformer.transformModelToEntity(cardModel)));
        }
    }

    @Override
    public void checkCard(String cardNumber, String cvvCode, Long userId) throws ExceptionNotFound {
        Optional<Card> card = cardRepository.findCardByUserAndCardNumberAndCvvCode(userId, cardNumber, cvvCode);
        if(!card.isPresent()){
            throw new ExceptionNotFound("The card or cvv code are not valid.");
        }
    }
}
