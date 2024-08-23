package com.microservices.cards.Service.Impl;

import com.microservices.cards.Constants.CardsConstants;
import com.microservices.cards.Dto.CardsDto;
import com.microservices.cards.Entity.Cards;
import com.microservices.cards.Exception.CardAlreadyExistsException;
import com.microservices.cards.Exception.ResourceNotFoundException;
import com.microservices.cards.Mapper.CardsMapper;
import com.microservices.cards.Repository.CardsRepository;
import com.microservices.cards.Service.CardsService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.smartcardio.CardNotPresentException;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor

public class CardsServiceImpl implements CardsService {

    private CardsRepository cardsRepository;


    @Override
    public void createCard(String mobileNumber) {

        Optional<Cards> cardsOptional = cardsRepository.findByMobileNumber(mobileNumber);
        if(cardsOptional.isPresent())throw new CardAlreadyExistsException("Card is already registered under this mobile number :"+mobileNumber);
        cardsRepository.save(createNewCard(mobileNumber));

    }

    private Cards createNewCard(String mobileNumber){
        Cards card = new Cards();
        card.setMobileNumber(mobileNumber);
        card.setCardType(CardsConstants.CREDIT_CARD);
        card.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        card.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        card.setCardNumber(Long.toString(randomCardNumber));
        return card;

    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card","mobileNumber",mobileNumber)
        );
        return CardsMapper.mapToCardsDto(card,new CardsDto());


    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards card = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card","cardNumber",cardsDto.getCardNumber())
        );
         card = CardsMapper.mapToCards(cardsDto,card);
         cardsRepository.save(card);
         return true;

    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card","mobileNumber",mobileNumber)
        );
        cardsRepository.delete(card);
        return true;
    }
}
