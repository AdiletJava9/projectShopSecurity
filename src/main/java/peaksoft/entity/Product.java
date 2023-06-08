package peaksoft.entity;
import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Category;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(generator = "product_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "product_gen", sequenceName = "product_seq", allocationSize = 1)
    private Long id;
    private String name;
    private Integer price;
    private String images;
    private String characteristic;
    private Boolean isFavorite;
    private String madeIn;
    private Category category;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Basket>baskets;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Brand brand;
    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy = "product")
    private List<Comment>comments;
    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy = "product")
    private List<Favorite>favorites;
}
