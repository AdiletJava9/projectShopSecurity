package peaksoft.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import peaksoft.enums.Category;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private Integer price;
    private String images;
    private String characteristic;
    private String madeIn;
    @Enumerated(EnumType.STRING)
    private Category category;

    public ProductResponse(Long id, String name, Integer price, String images, String characteristic, String madeIn, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.characteristic = characteristic;
        this.madeIn = madeIn;
        this.category = category;
    }
}
