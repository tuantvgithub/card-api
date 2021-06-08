package hust.soict.hedspi.backend.service;

import hust.soict.hedspi.backend.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CardService {
    List<CardBriefDto>      getAllCardBrief(String category, String provider);
    List<CardBriefDto>      getAllCardBrief();
    List<CardBriefDto>      getAllCardBriefByCategory(String category);
    List<CardBriefDto>      getAllCardBriefByProvider(String provider);
    List<CardBriefDto>      getAllCardBriefByCategoryAndProvider(String category, String provider);
    List<CardComparisonDto> getListCardComparisonByIds(String ids);

    Page<CardBriefDto>      getPageCardBrief(String category, String provider, int page, int size);
    Page<CardBriefDto>      getPageCardBrief(int page, int size);
    Page<CardBriefDto>      getPageCardBriefByCategory(String category, int page, int size);
    Page<CardBriefDto>      getPageCardBriefByProvider(String provider, int page, int size);
    Page<CardBriefDto>      getPageCardBriefByCategoryAndProvider(String category, String provider,
                                                                  int page, int size);

    CardDetailDto           getCardDetailById(Long id);
    CardDetailDto           createNewCard(CardFormDto formDto);
    CardDetailDto           updateCard(Long id, CardFormDto formDto);
    CardDetailDto           updateSpecificFields(Long id, Map<String, Object> fields);
    void                    deleteCardById(Long id);
}
