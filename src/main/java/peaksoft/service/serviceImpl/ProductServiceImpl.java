package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.ProductRequest;
import peaksoft.dto.ProductResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.entity.Product;
import peaksoft.entity.User;
import peaksoft.enums.Category;
import peaksoft.repository.ProductRepository;
import peaksoft.service.ProductService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public SimpleResponse saveProduct(ProductRequest productRequest,Long brandId) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setCharacteristic(productRequest.getCharacteristic());
        product.setImages(productRequest.getImages());
        product.setPrice(productRequest.getPrice());
        product.setMadeIn(productRequest.getMadeIn());
        productRepository.save(product);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Product with name %s is successfully saved", productRequest.getName()))
                .build();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findProductById(id).orElseThrow(() ->
                new NullPointerException("Product with id:" + id + " not found"));
    }

    @Override
    public SimpleResponse updateProduct(Long id, ProductRequest productRequest) {
      Product product =  productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("Product with id:" + id + " not found"));
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setCharacteristic(productRequest.getCharacteristic());
        product.setImages(productRequest.getImages());
        product.setPrice(productRequest.getPrice());
        product.setMadeIn(productRequest.getMadeIn());
        productRepository.save(product);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Product with name %s is successfully updated", productRequest.getCategory()))
                .build();
    }

    @Override
    public SimpleResponse deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("Product with id: " + id + " not found"));
        productRepository.delete(product);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Product with id %s is successfully deleted", id))
                .build();
    }
}
