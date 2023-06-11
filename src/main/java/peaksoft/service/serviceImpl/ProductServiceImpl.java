package peaksoft.service.serviceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.*;
import peaksoft.entity.*;
import peaksoft.repository.CommentRepository;
import peaksoft.repository.FavoriteRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.ProductService;
import java.time.ZonedDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;


    @Override
    public SimpleResponse saveProduct(ProductRequest productRequest) {
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
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NullPointerException("Product with id: " + id + " not found");
        }
        int countLikes = favoriteRepository.countByProductId(id);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setCategory(product.getCategory());
        productResponse.setImages(product.getImages());
        productResponse.setCharacteristic(product.getCharacteristic());
        productResponse.setPrice(product.getPrice());
        productResponse.setMadeIn(product.getMadeIn());
        productResponse.setLikesCount(countLikes);
        return productResponse;
    }

    @Override
    public SimpleResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() ->
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

    @Override
    public SimpleResponse commentToProduct(Long productId, String comment) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new NullPointerException("Product with id: " + productId + " not found"));
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        comment1.setCreateDate(ZonedDateTime.now());
        comment1.setUser(comment1.getUser());
        comment1.setProduct(product);
        product.getComments().add(comment1);
        commentRepository.save(comment1);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("You left a comment with id: %s ", productId))
                .build();
    }

    @Override
    public SimpleResponse likeToProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (product == null || user == null) {
            System.out.println("not not not not not not");
        }
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        assert product != null;
        product.getFavorites().add(favorite);
        productRepository.save(product);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Like added with product id: " + productId))
                .build();
    }
}
