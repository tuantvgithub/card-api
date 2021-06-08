package hust.soict.hedspi.backend.dto;

import hust.soict.hedspi.backend.model.Card;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class CardDetailDto {
    private Long id;
    private String name;
    private String category;
    private String provider;
    private String annualFee;
    private List<String> imageUrls;

    private String rank;
    private List<String> features;
    private Map<String, String> transactionalLimits;
    private Map<String, String> serviceCosts;

    private Date updateDate;
    private List<String> advices;

    public CardDetailDto(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.category = card.getCategory();
        this.provider = card.getProvider();
        this.annualFee = card.getAnnualFee();
        this.rank = card.getRank();
        this.updateDate = card.getUpdateDate();
        this.advices = card.getAdvices();
        this.imageUrls = card.getImageUrls();
        this.features = card.getFeatures();
        this.transactionalLimits = card.getTransactionalLimits();
        this.serviceCosts = card.getServiceCosts();
    }
}
