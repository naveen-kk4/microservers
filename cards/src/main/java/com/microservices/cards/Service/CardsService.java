package com.microservices.cards.Service;

import com.microservices.cards.Dto.CardsDto;

public interface CardsService {

     void createCard(String mobileNumber);

     CardsDto fetchCard(String mobileNumber);

     boolean updateCard(CardsDto cardsDto);

     boolean deleteCard(String mobileNumber);
}
