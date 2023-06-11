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
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setCategory(product.getCategory());
        response.setImages(product.getImages());
        response.setCharacteristic(product.getCharacteristic());
        response.setPrice(product.getPrice());
        response.setMadeIn(product.getMadeIn());
        response.setLikesCount(countLikes);
        return response;
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

//    @Override
//    public String commentToProduct(Long productId, CommentRequest commentRequests) {
//        Product product = productRepository.findById(productId).orElseThrow(() ->
//                new NullPointerException("Product with id: " + productId + " not found"));
//        Comment comment = new Comment();
//        comment.setComment(commentRequests.getComment());
//        comment.setCreateDate(ZonedDateTime.now());
//        List<Comment> comments = new ArrayList<>();
//        comments.add(comment);
//        product.setComments(comments);
//        return "comment saved " + productId;
//    }
//
//    @Override
//    public SimpleResponse basketToProduct(Long productId, Basket basket) {
//        Product product = productRepository.findById(productId).orElseThrow(() ->
//                new NullPointerException("Product with id: " + productId + " not found"));
//        Basket basket1 = new Basket();
//        basket1.setProducts(basket.getProducts());
//        basket1.setUser(basket.getUser());
//        List<Basket> baskets = new ArrayList<>();
//        baskets.add(basket1);
//        product.setBaskets(baskets);
//        return SimpleResponse.builder()
//                .status(HttpStatus.OK)
//                .message(String.format("Product with name %s is baskets ", product.getName()))
//                .build();
//    }

    @Override
    public void commentToProduct(Long productId, String comment) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new NullPointerException("Product with id: " + productId + " not found"));
        Comment comment1 = new Comment();
        comment1.setProduct(product);
        product.getComments().add(comment1);
        commentRepository.save(comment1);
        System.out.println("Comment added to with product id: " + productId);
    }

    @Override
    public void likeToProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (product == null || user == null) {
            System.out.println("not not not not not not");
            return;
        }
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        product.getFavorites().add(favorite);
        productRepository.save(product);
        System.out.println("Like added with product id: " + productId);
    }
}
