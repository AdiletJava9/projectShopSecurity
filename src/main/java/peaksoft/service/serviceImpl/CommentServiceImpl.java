package peaksoft.service.serviceImpl;

import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.CommentRequest;
import peaksoft.dto.CommentResponse;
import peaksoft.dto.ProductResponse;
import peaksoft.entity.Comment;
import peaksoft.entity.Product;
import peaksoft.repository.CommentRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    @Override
    public String commentToProduct(Long productId, CommentRequest commentRequest) {
        return null;
    }
}
