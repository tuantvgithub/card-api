package hust.soict.hedspi.backend.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CardFormDto {
    private Long id;
    private String name;
    private String category;
    private String provider;
    private String annualFee;
    private List<String> imageUrls;

    private String rank;
    private List<String> advices;
    private List<String> features;
    private Map<String, String> transactionalLimits;
    private Map<String, String> serviceCosts;
}
