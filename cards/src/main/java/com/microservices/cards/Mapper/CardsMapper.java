package com.microservices.cards.Mapper;

import com.microservices.cards.Dto.CardsDto;
import com.microservices.cards.Entity.Cards;

public class CardsMapper {

    public static CardsDto mapToCardsDto(Cards cards , CardsDto cardsDto){
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        return cardsDto;
    }

    public static Cards mapToCards(CardsDto cardsDto , Cards cards){
         cards.setAmountUsed(cardsDto.getAmountUsed());
         cards.setCardNumber(cardsDto.getCardNumber());
         cards.setCardType(cardsDto.getCardType());
         cards.setMobileNumber(cardsDto.getMobileNumber());
         cards.setTotalLimit(cardsDto.getTotalLimit());
         cards.setAvailableAmount(cardsDto.getAvailableAmount());
        return cards;
    }
}
