package hust.soict.hedspi.backend.service.implement;

import hust.soict.hedspi.backend.dto.*;
import hust.soict.hedspi.backend.mapping.CardMapping;
import hust.soict.hedspi.backend.model.Card;
import hust.soict.hedspi.backend.repository.CardMongoRepository;
import hust.soict.hedspi.backend.service.CardService;
import hust.soict.hedspi.backend.web.exception.CardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardServiceImpl implements CardService {
    private final CardMapping mapping;
    private final CardMongoRepository mongoRepository;

    @Autowired
    public CardServiceImpl(CardMapping mapping, CardMongoRepository mongoRepository) {
        this.mapping = mapping;
        this.mongoRepository = mongoRepository;
    }

    @Override
    public List<CardBriefDto> getAllCardBrief(String category, String provider) {
        if (category == null && provider == null)
            return this.getAllCardBrief();
        if (category == null)
            return this.getAllCardBriefByProvider(provider);
        if (provider == null)
            return this.getAllCardBriefByCategory(category);
        return this.getAllCardBriefByCategoryAndProvider(category, provider);
    }

    @Override
    public List<CardBriefDto> getAllCardBrief() {
        List<Card> cardList = this.mongoRepository.findAll();
        List<CardBriefDto> cardBriefDtoList = new ArrayList<CardBriefDto>();

        for (Card card : cardList) {
            cardBriefDtoList.add(new CardBriefDto(card));
        }

        return cardBriefDtoList;
    }

    @Override
    public List<CardBriefDto> getAllCardBriefByCategory(String category) {
        category = category.replace("-", " ");
        List<Card> cardList = this.mongoRepository.findByCategory(category);
        List<CardBriefDto> cardBriefDtoList = new ArrayList<CardBriefDto>();

        for (Card card : cardList) {
            cardBriefDtoList.add(new CardBriefDto(card));
        }

        return cardBriefDtoList;
    }

    @Override
    public List<CardBriefDto> getAllCardBriefByProvider(String provider) {
        provider = provider.replace("-", " ");
        List<Card> cardList = this.mongoRepository.findByProvider(provider);
        List<CardBriefDto> cardBriefDtoList = new ArrayList<CardBriefDto>();

        for (Card card : cardList) {
            cardBriefDtoList.add(new CardBriefDto(card));
        }

        return cardBriefDtoList;
    }

    @Override
    public List<CardBriefDto> getAllCardBriefByCategoryAndProvider(
            String category, String provider
    ) {
        category = category.replace("-", " ");
        provider = provider.replace("-", " ");
        List<CardBriefDto> cardBriefDtos = new ArrayList<CardBriefDto>();
        List<Card> cards = this.mongoRepository.findByCategoryAndProvider(category, provider);

        for (Card card : cards) {
            cardBriefDtos.add(new CardBriefDto(card));
        }

        return cardBriefDtos;
    }

    @Override
    public Page<CardBriefDto> getPageCardBrief(String category, String provider, int page, int size) {
        if (category == null && provider == null)
            return this.getPageCardBrief(page, size);
        if (category == null)
            return this.getPageCardBriefByProvider(provider, page, size);
        if (provider == null)
            return this.getPageCardBriefByCategory(category, page, size);
        return this.getPageCardBriefByCategoryAndProvider(category, provider, page, size);
    }

    @Override
    public Page<CardBriefDto> getPageCardBrief(int page, int size) {
        Page<Card> cardPage = this.mongoRepository.findAll(PageRequest.of(page-1, size));

        return cardPage.map(CardBriefDto::new);
    }

    @Override
    public Page<CardBriefDto> getPageCardBriefByCategory(String category, int page, int size) {
        Page<Card> cardPage = this.mongoRepository
                                    .findByCategory(category, PageRequest.of(page-1, size));

        return cardPage.map(CardBriefDto::new);
    }

    @Override
    public Page<CardBriefDto> getPageCardBriefByProvider(String provider, int page, int size) {
        Page<Card> cardPage = this.mongoRepository
                .findByProvider(provider, PageRequest.of(page-1, size));

        return cardPage.map(CardBriefDto::new);
    }

    @Override
    public Page<CardBriefDto> getPageCardBriefByCategoryAndProvider(String category, String provider, int page, int size) {
        Page<Card> cardPage = this.mongoRepository
                .findByCategoryAndProvider(category, provider, PageRequest.of(page-1, size));

        return cardPage.map(CardBriefDto::new);
    }

    @Override
    public CardDetailDto getCardDetailById(Long id) {
        Optional<Card> cardOptional = this.mongoRepository.findById(id);

        return cardOptional.map(CardDetailDto::new).orElseThrow(
                () -> new CardNotFoundException("card by id " + id + " was not found")
        );
    }

    @Override
    public List<CardComparisonDto> getListCardComparisonByIds(String ids) {
        String[] idArray = ids.split(",");
        List<CardComparisonDto> comparisonDtos = new ArrayList<CardComparisonDto>();

        for (String s : idArray) {
            Optional<Card> cardOptional = this.mongoRepository
                    .findById(Long.parseLong(s));
            cardOptional.ifPresent(card -> comparisonDtos.add(new CardComparisonDto(card)));
        }

        return comparisonDtos;
    }

    @Override
    public CardDetailDto createNewCard(CardFormDto formDto) {
        Card card = this.mapping.formDtoToModel(formDto);

        card.setUpdateDate(new Date());
        Card newCard = this.mongoRepository.save(card);

        return new CardDetailDto(newCard);
    }

    @Override
    public CardDetailDto updateCard(Long id, CardFormDto formDto) {
        Optional<Card> cardOptional = this.mongoRepository.findById(id);

        if (cardOptional.isEmpty()) {
            throw new CardNotFoundException("card by id " + id + " was not found");
        }

        Card card = this.mongoRepository.save(this.mapping.formDtoToModel(formDto));

        return new CardDetailDto(card);
    }

    @Override
    public CardDetailDto updateSpecificFields(Long id, Map<String, Object> fields) {
        Optional<Card> cardOptional = this.mongoRepository.findById(id);

        if (cardOptional.isEmpty())
            throw new CardNotFoundException("card by id " + id + " was not found");
        Card card = cardOptional.get();
        // TO DO

        return null;
    }

    @Override
    public void deleteCardById(Long id) {
        Optional<Card> cardOptional = this.mongoRepository.findById(id);

        if (cardOptional.isEmpty()) {
            throw new CardNotFoundException("card by id " + id + " was not found");
        }

        this.mongoRepository.deleteById(id);
    }
}
