package hust.soict.hedspi.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "cards")
@Data
public class Card {
    @Id
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
}
