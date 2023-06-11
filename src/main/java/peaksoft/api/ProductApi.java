package peaksoft.api;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.*;
import peaksoft.entity.Comment;
import peaksoft.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductApi {
    private final ProductService productService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveProduct(@RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    @PermitAll
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PermitAll
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
    @PostMapping("/{id}/comments")
    public ResponseEntity<Void> addCommentToProduct(@PathVariable Long id,
                                                    @RequestParam String comment) {
        productService.commentToProduct(id, comment);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{productId}/like")
    public ResponseEntity<Void> addLikeToProduct(@PathVariable Long productId,
                                                 @RequestParam Long userId) {
        productService.likeToProduct(productId, userId);
        return ResponseEntity.ok().build();
    }
}

