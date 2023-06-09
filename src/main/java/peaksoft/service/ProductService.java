package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Product;
import peaksoft.enums.Category;
import peaksoft.repository.ProductRepository;

import java.util.List;

@Service
public interface ProductService {
//    private final ProductRepository productRepository;
//    @Autowired
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//    public List<Product> getProductsByCategoryAndPrice(Category category, double price) {
//        return productRepository.findByCategoryAndPriceLessThanEqualOrderByPriceAsc(category, price);
//    }
}
