package hust.soict.hedspi.backend.mapping;

import hust.soict.hedspi.backend.dto.CardFormDto;
import hust.soict.hedspi.backend.model.Card;

public interface CardMapping {
    Card formDtoToModel(CardFormDto formDto);
}
