package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "brands")
@AllArgsConstructor
@Builder
public class Brand {
    @Id
    @GeneratedValue(generator = "brand_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "brand_gen", sequenceName = "brand_seq", allocationSize = 1)
    private Long id;
    private String brandName;
    private String image;
    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy = "brand")
    private List<Product>products;
}
