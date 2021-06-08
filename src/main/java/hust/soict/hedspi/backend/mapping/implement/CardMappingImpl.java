package hust.soict.hedspi.backend.mapping.implement;

import hust.soict.hedspi.backend.dto.CardFormDto;
import hust.soict.hedspi.backend.mapping.CardMapping;
import hust.soict.hedspi.backend.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMappingImpl implements CardMapping {

    @Override
    public Card formDtoToModel(CardFormDto formDto) {
        Card card = new Card();

        card.setId(formDto.getId());
        card.setName(formDto.getName());
        card.setCategory(formDto.getCategory());
        card.setProvider(formDto.getProvider());
        card.setRank(formDto.getRank());
        card.setAnnualFee(formDto.getAnnualFee());
        card.setAdvices(formDto.getAdvices());
        card.setFeatures(formDto.getFeatures());
        card.setServiceCosts(formDto.getServiceCosts());
        card.setTransactionalLimits(formDto.getTransactionalLimits());
        card.setImageUrls(formDto.getImageUrls());

        return card;
    }

}
