package hust.soict.hedspi.backend.dto;

import hust.soict.hedspi.backend.model.Card;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CardComparisonDto {
    private Long id;
    private String name;
    private String category;
    private String provider;
    private String imageUrl;

    private String rank;
    private List<String> features;
    private Map<String, String> transactionalLimits;
    private Map<String, String> serviceCosts;
    private List<String> advices;

    public CardComparisonDto(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.category = card.getCategory();
        this.provider = card.getProvider();
        this.imageUrl = card.getImageUrls().get(0);
        this.rank = card.getRank();
        this.advices = card.getAdvices();
        this.features = card.getFeatures();
        this.transactionalLimits = card.getTransactionalLimits();
        this.serviceCosts = card.getServiceCosts();
    }
}
