package peaksoft.service;

import peaksoft.dto.*;
import peaksoft.entity.Product;
import peaksoft.enums.Category;

import java.util.List;

public interface ProductService {

    SimpleResponse saveProduct(ProductRequest productRequest,Long brandId);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    SimpleResponse updateProduct(Long id, ProductRequest productRequest);

    SimpleResponse deleteProductById(Long id);
}
