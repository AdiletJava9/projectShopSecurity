package peaksoft.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "baskets")
@AllArgsConstructor
@Builder
public class Basket {
    @Id
    @GeneratedValue(generator = "basket_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "basket_gen", sequenceName = "basket_seq", allocationSize = 1)
    private Long id;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy = "baskets")
    private List<Product>products;
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private User user;

}
