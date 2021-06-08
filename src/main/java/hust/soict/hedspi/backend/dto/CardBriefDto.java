package hust.soict.hedspi.backend.dto;

import hust.soict.hedspi.backend.model.Card;
import lombok.Data;

@Data
public class CardBriefDto {
    private Long id;
    private String name;
    private String category;
    private String provider;
    private String annualFee;
    private String imageUrl;

    public CardBriefDto(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.provider = card.getProvider();
        this.category = card.getCategory();
        this.annualFee = card.getAnnualFee();
        this.imageUrl = card.getImageUrls().get(0);
    }
}
