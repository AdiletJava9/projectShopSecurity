package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Product;
import peaksoft.enums.Category;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryAndPriceLessThanEqualOrderByPriceAsc(Category category, double price);
}
