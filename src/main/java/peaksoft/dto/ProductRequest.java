package peaksoft.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import peaksoft.enums.Category;

@Data
@Builder
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private Integer price;
    private String images;
    private String characteristic;
    private String madeIn;
    @Enumerated(EnumType.STRING)
    private Category category;
}
