package peaksoft.service;

import peaksoft.dto.CommentRequest;
import peaksoft.dto.CommentResponse;

public interface CommentService {
    String commentToProduct(Long productId,CommentRequest commentRequest);
}
